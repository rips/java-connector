package com.ripstech.api.entity.generator.entity

import com.ripstech.api.entity.generator.ClassNaming
import com.ripstech.api.entity.generator.TypeGuessingCommon
import com.ripstech.api.entity.generator.TypeNaming
import com.squareup.javapoet.*
import io.swagger.v3.oas.models.media.Schema
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.*
import javax.lang.model.element.Modifier

abstract class RipsEntity(name: String) {

    private val typeGuessing = TypeGuessingCommon()
    protected val fullClassName = ClassNaming.naming(name)

    fun packageName(subPackage: String) =
        sequenceOf("com.ripstech.api.entity", subPackage, fullClassName.packageName)
                .filter { it.isNotBlank() }
                .joinToString(".")

    abstract fun build(): JavaFile

    protected open fun wrapTypeName(typeName: TypeName) = typeName

    fun buildFields(name: String, schema: Schema<out Any>, modifier: Modifier, clazz: ClassName, vararg methodType: METHOD_TYPE): Triple<FieldSpec, List<MethodSpec>, TypeSpec?> {
        val (safeName, annotation) = TypeNaming.name(name)
        val (typeName, initializer, innerClass) = typeGuessing.guess(name, schema, clazz)
        val parameterSpec = ParameterSpec.builder(typeName, safeName, Modifier.FINAL)
                .addAnnotation(Nullable::class.java)
                .build()
        return Triple(FieldSpec.builder(wrapTypeName(typeName), safeName)
                .addModifiers(modifier)
                .addAnnotations(listOfNotNull(annotation))
                .also { if (this is RipsEntityReceive) initializer?.apply { it.initializer(this) } }
                .build(),
                listOfNotNull(
                        if (methodType.contains(METHOD_TYPE.GET))
                            MethodSpec.methodBuilder("get" + safeName.capitalize())
                                    .addModifiers(Modifier.PUBLIC)
                                    .addStatement("return this.\$N", safeName)
                                    .returns(wrapTypeName(typeName))
                                    .build()
                        else null,
                        if (methodType.contains(METHOD_TYPE.SET))
                            MethodSpec.methodBuilder("set" + safeName.capitalize())
                                    .addModifiers(Modifier.PUBLIC)
                                    .addParameter(parameterSpec)
                                    .addStatement("this.\$N = \$T.ofNullable(\$N)", safeName, ClassName.get(Optional::class.java), safeName)
                                    .addStatement(CodeBlock.of("return this"))
                                    .returns(clazz)
                                    .build()
                        else null
                ),
                innerClass)
    }

    fun buildConstructor(required: Map<String, TypeName>): MethodSpec {
        val requiredProperties = required.map {
            ParameterSpec.builder(it.value, it.key, Modifier.FINAL)
                    .addAnnotation(NotNull::class.java)
                    .build() to
                    CodeBlock.of("this.\$N = \$T.of(\$N)", it.key, ClassName.get(Optional::class.java), it.key)
        }
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameters(requiredProperties.map { it.first })
                .also { builder ->
                    requiredProperties.map { it.second }
                            .takeIf { it.isNotEmpty() }
                            ?.apply { builder.addStatement(CodeBlock.join(this, ";\n")) }
                }
                .build()
    }

    enum class METHOD_TYPE {
        GET,
        SET
    }

}