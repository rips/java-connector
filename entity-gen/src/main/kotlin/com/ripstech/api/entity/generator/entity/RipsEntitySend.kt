package com.ripstech.api.entity.generator.entity

import com.fasterxml.jackson.annotation.JsonRootName
import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import io.swagger.v3.oas.models.media.Schema
import java.util.Optional
import javax.lang.model.element.Modifier
import org.apache.logging.log4j.kotlin.Logging

class RipsEntitySend(
    name: String,
    post: Schema<Any>? = null,
    patch: Schema<Any>? = null,
    put: Schema<Any>? = null
) : RipsEntity(name), Logging {

    private var commonProperties: Map<String, Map<Method, Schema<out Any>>> = mapOf()
    private val methodProperties: MutableMap<Method, Map<String, Schema<out Any>>> = mutableMapOf()
    private val required: MutableMap<Method, Set<String>> = mutableMapOf()

    init {
        val postPropertySchemas = post?.properties?.toMap() ?: emptyMap()
        val patchPropertySchemas = patch?.properties?.toMap() ?: emptyMap()
        val putPropertySchemas = put?.properties?.toMap() ?: emptyMap()

        val commonPropertyNames = listOf(postPropertySchemas, patchPropertySchemas, putPropertySchemas)
                .filter { it.isNotEmpty() }
                .takeIf { it.isNotEmpty() }
                ?.asSequence()
                ?.map { it.keys }
                ?.reduce { l1, l2 -> l1.intersect(l2) }
                ?: emptySet()

        commonProperties = commonPropertyNames.asSequence()
                .map {
                    it to mapOf(
                            Method.POST to postPropertySchemas[it],
                            Method.PATCH to patchPropertySchemas[it],
                            Method.PUT to putPropertySchemas[it]
                    ).filterValues { schema ->
                        schema != null
                    }.mapValues { entry -> entry.value!! }
                }
                .associate { it }

        addMethodProperties(Method.POST, postPropertySchemas)
        addMethodProperties(Method.PATCH, patchPropertySchemas)
        addMethodProperties(Method.PUT, putPropertySchemas)
    }

    private fun addMethodProperties(method: Method, schema: Map<String, Schema<out Any>>) {
        if (schema.isNotEmpty()) {
            methodProperties[method] = emptyMap()
        }
        (schema - commonProperties.keys)
                .takeIf { it.isNotEmpty() }
                ?.apply { methodProperties[method] = this }
    }

    init {
        post?.required?.apply { required[Method.POST] = this.toSet() }
        patch?.required?.apply { required[Method.PATCH] = this.toSet() }
        put?.required?.apply { required[Method.PUT] = this.toSet() }
    }

    override fun wrapTypeName(typeName: TypeName) = ParameterizedTypeName.get(ClassName.get(Optional::class.java), typeName)!!

    override fun build(): JavaFile {
        val outerClassName = ClassName.get(packageName("send"), "${fullClassName.name}Send")
        val clazz = TypeSpec.classBuilder(outerClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addAnnotation(AnnotationSpec.builder(JsonRootName::class.java)
                        .addMember("value", "\$S", fullClassName.name.toLowerCase())
                        .build()
                )

        val commonFields = commonProperties.map {
            val distinct = it.value.toList().distinctBy { listOf(it.second.format, it.second.type, it.second.properties, it.second.`$ref`) }
            if (distinct.size > 1) {
                logger.warn("Schema of $outerClassName::${it.key} in ${distinct.map { it.first }} are different.")
            }
            buildFields(it.key, distinct.first().second, Modifier.PROTECTED, outerClassName, METHOD_TYPE.GET, METHOD_TYPE.SET)
        }
        clazz.addFields(commonFields.map { it.first })
                .addMethods(commonFields.flatMap { it.second }.filterNot { it.name.startsWith("set") })
                .addTypes(commonFields.mapNotNull { it.third })

        methodProperties.map { properties ->
            val innerClassName = outerClassName.nestedClass(properties.key.name.toLowerCase().capitalize())
            val innerClass = TypeSpec.classBuilder(innerClassName)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .superclass(outerClassName)

            val allFields = commonFields.map { it.first }.toMutableList()

            properties.value.map { buildFields(it.key, it.value, Modifier.PRIVATE, innerClassName, METHOD_TYPE.SET, METHOD_TYPE.GET) }
                    .apply {
                        allFields.addAll(this.map { it.first })
                        innerClass.addFields(this.map { it.first })
                                .addMethods(this.flatMap { it.second })
                                .addTypes(this.mapNotNull { it.third })
                    }

            (this.required[properties.key] ?: emptySet()).asSequence()
                    .map { name ->
                        name to (allFields.find { it.name == name }?.type ?: TypeName.OBJECT)
                    }
                    .associate { it }
                    .mapValues {
                        if (it.value is ParameterizedTypeName &&
                                (it.value as ParameterizedTypeName).rawType == ClassName.get(Optional::class.java)) {
                            (it.value as ParameterizedTypeName).typeArguments.first()
                        } else {
                            it.value
                        }
                    }
                    .apply { innerClass.addMethod(buildConstructor(this)) }

            commonFields.flatMap { it.second }
                    .asSequence()
                    .filter { it.name.startsWith("set") }
                    .map { it.toBuilder()
                            .returns(innerClassName)
                            .build()
                    }
                    .toList()
                    .apply { innerClass.addMethods(this) }

            innerClass.build()
        }.apply { clazz.addTypes(this) }

        return JavaFile.builder(outerClassName.packageName(), clazz.build()).build()
    }

    enum class Method {
        POST,
        PATCH,
        PUT,
    }
}
