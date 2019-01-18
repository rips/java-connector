package com.ripstech.api.entity.generator.entity

import com.fasterxml.jackson.annotation.JsonRootName
import com.squareup.javapoet.*
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import org.apache.logging.log4j.kotlin.Logging
import java.util.*
import javax.lang.model.element.Modifier

class RipsEntityArraySend(val name: String, val schema: Schema<Any>): RipsEntity(name), Logging {

    override fun wrapTypeName(typeName: TypeName) = ParameterizedTypeName.get(ClassName.get(Optional::class.java), typeName)!!

    override fun build(): JavaFile {
        val outerClassName = ClassName.get(packageName("send"), "${fullClassName.name}Send")
        val elementClassName = outerClassName.nestedClass("Element")
        val postElementClassName = elementClassName.nestedClass("Post")

        var superClass = ParameterizedTypeName.get(ClassName.get(ArrayList::class.java), elementClassName)
        var constructorParameter = ArrayTypeName.of(elementClassName)

        val fields =
                when(schema) {
                    is ObjectSchema -> schema.properties
                    is ArraySchema -> {
                        superClass = ParameterizedTypeName.get(ClassName.get(ArrayList::class.java),
                                ParameterizedTypeName.get(ClassName.get(List::class.java), elementClassName))
                        constructorParameter = ArrayTypeName.of(ParameterizedTypeName.get(ClassName.get(List::class.java), elementClassName))
                        schema.items.properties
                    }
                    else -> {
                        logger.error("Cannot process Array Entity")
                        emptyMap()
                    }
                }
                .mapValues {
                    buildFields(it.key, it.value, Modifier.PROTECTED, postElementClassName, METHOD_TYPE.GET, METHOD_TYPE.SET)
                }

        val innerTypes = fields
                .filterValues { it.third != null }
                .map { it.key to it.value.third!! }
                .map {
                    val schema = schema.properties[it.first]
                    var type = it.second
                    when(schema) {
                        is ArraySchema -> {
                            val subFields = schema.items
                                    .properties
                                    .mapValues { itemProp ->
                                        val subTypeName = elementClassName.nestedClass(it.second.name)
                                        buildFields(itemProp.key, itemProp.value, Modifier.PROTECTED, subTypeName, METHOD_TYPE.GET, METHOD_TYPE.SET)
                                    }
                            type = type.toBuilder()
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                    .addFields(subFields.map { it.value.first })
                                    .addMethods(subFields.flatMap { it.value.second })
                                    .build()
                        }
                        else -> logger.error("not supported")
                    }
                    type
                }

        val requiredFields = schema.required
                ?.asSequence()
                ?.map { it to fields[it]!!.first.type }
                ?.associate { it }
                ?.mapValues {
                    if (it.value is ParameterizedTypeName &&
                            (it.value as ParameterizedTypeName).rawType == ClassName.get(Optional::class.java)) {
                        (it.value as ParameterizedTypeName).typeArguments.first()
                    } else {
                        it.value
                    }
                }

        val postElementClass = TypeSpec.classBuilder(postElementClassName)
                .superclass(elementClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .apply { if(requiredFields != null) {
                    this.addMethod(buildConstructor(requiredFields))
                } else {
                    logger.warn("$name has no required arguments")
                }}
                .addMethods(fields.flatMap { it.value.second }.filter { it.name.startsWith("set") })
                .build()

        val elementClass = TypeSpec.classBuilder(elementClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT, Modifier.STATIC)
                .addType(postElementClass)
                .addFields(fields.map { it.value.first })
                .addMethods(fields.flatMap { it.value.second }.filter { it.name.startsWith("get") })
                .addTypes(innerTypes)
                .build()

        val outerClass = TypeSpec.classBuilder(outerClassName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(JsonRootName::class.java)
                        .addMember("value", "\$S", fullClassName.name.toLowerCase())
                        .build()
                )
                .superclass(superClass)
                .addType(elementClass)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .varargs()
                        .addParameter(constructorParameter, "elements")
                        .addStatement("this.addAll(\$T.asList(elements))", ClassName.get(Arrays::class.java))
                        .build()
                )

        return JavaFile.builder(outerClassName.packageName(), outerClass.build()).build()
    }

}