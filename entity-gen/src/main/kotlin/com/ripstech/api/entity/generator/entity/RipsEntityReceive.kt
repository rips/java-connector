package com.ripstech.api.entity.generator.entity

import com.squareup.javapoet.*
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import org.apache.logging.log4j.kotlin.Logging
import javax.lang.model.element.Modifier

class RipsEntityReceive(name: String, val schema: Schema<Any>): RipsEntity(name), Logging {

    private fun innerSchemaBuilder(properties: Map<String, Schema<Any>>, subTypeName: ClassName, typeBuilder: TypeSpec.Builder) {
        properties
                .mapValues { buildFields(it.key, it.value, Modifier.PRIVATE, subTypeName, METHOD_TYPE.GET) }
                .asIterable()
                .partition { it.value.third != null }
                .run {
                    this.first
                            .takeIf { it.isNotEmpty() }
                            ?.associate { it.toPair() }
                            ?.apply { innerBuild(this, properties, subTypeName, typeBuilder) }
                    with(this.second) {
                        typeBuilder
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .addFields(this.map { it.value.first })
                                .addMethods(this.flatMap { it.value.second })
                    }
                }
    }

    private fun innerBuild(fields: Map<String, Triple<FieldSpec, List<MethodSpec>, TypeSpec?>>,
                           properties: Map<String, Schema<Any>>,
                           outerClassName: ClassName,
                           clazz: TypeSpec.Builder) {
        val innerTypes = fields
                .filterValues { it.third != null }
                .map { it.key to it.value.third!! }
                .map {
                    val schema = properties[it.first]
                    val typeBuilder = it.second.toBuilder()
                    if (schema != null) {
                        when (schema) {
                            is ObjectSchema -> {
                                innerSchemaBuilder(schema.properties, outerClassName.nestedClass(it.second.name), typeBuilder)
                            }
                            is ArraySchema -> {
                                innerSchemaBuilder(schema.items.properties, outerClassName.nestedClass(it.second.name), typeBuilder)
                            }
                            else -> logger.error("not supported ${schema::class.java}")
                        }
                    }
                    typeBuilder.build()
                }

        clazz.addFields(fields.map { it.value.first })
                .addMethods(fields.flatMap { it.value.second })
                .addTypes(innerTypes)
    }

    override fun build(): JavaFile {
        val outerClassName = ClassName.get(packageName("receive"), fullClassName.name)
        val clazz = TypeSpec.classBuilder(outerClassName)
                .addModifiers(Modifier.PUBLIC)

        schema.properties
                .mapValues { buildFields(it.key, it.value, Modifier.PRIVATE, outerClassName, METHOD_TYPE.GET) }
                .apply { innerBuild(this, schema.properties, outerClassName, clazz) }

        return JavaFile.builder(outerClassName.packageName(), clazz.build()).build()
    }

}