package com.ripstech.api.entity

import com.ripstech.api.entity.generator.entity.RipsEntity
import com.ripstech.api.entity.generator.entity.RipsEntityArraySend
import com.ripstech.api.entity.generator.entity.RipsEntityReceive
import com.ripstech.api.entity.generator.entity.RipsEntitySend
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.parser.OpenAPIV3Parser
import java.nio.file.Paths
import javax.lang.model.element.Modifier
import org.apache.logging.log4j.kotlin.logger

object Log {
    var logger = logger()
}

object IdHolder {
    val className = ClassName.get("com.ripstech.api.entity.helper", "IdHolder")!!
    val javaFile = JavaFile.builder(
            className.packageName(),
            TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(TypeName.LONG, "id", Modifier.PRIVATE)
                    .addMethod(
                            MethodSpec.methodBuilder("getId")
                                    .addModifiers(Modifier.PUBLIC)
                                    .addStatement("return this.\$N", "id")
                                    .returns(TypeName.LONG)
                                    .build()
                    )
                    .build())
            .build()
}

fun main(args: Array<String>) {

    var logger = Log.logger

    val openAPI = OpenAPIV3Parser().read("swagger.yaml")

    val receiveEntities = openAPI.components
            .schemas
            .filterKeys { s -> !s.contains("""^(Post|Patch|Put).""".toRegex()) }
            .mapNotNull {
                when (it.value) {
                    is ObjectSchema -> RipsEntityReceive(it.key, it.value)
                    else -> {
                        logger.error("not implemented scheme: ${it.key}")
                        null
                    }
                }
            }

    // first stage
    val sendEntities = openAPI.components
            .schemas
            .filterKeys { s -> s.contains("""^(Post|Patch|Put).""".toRegex()) }
            .asIterable()
            .groupBy {
                val components = it.key.split(".Sub.")
                if (components.size <= 1) {
                    it.key.removePrefix("Post.").removePrefix("Put.")
                } else {
                    components.last()
                }
            }
            .mapNotNull {
                val post = it.value.find { schemaName -> schemaName.key.startsWith("Post") }?.value
                val patch = it.value.find { schemaName -> schemaName.key.startsWith("Patch") }?.value
                val put = it.value.find { schemaName -> schemaName.key.startsWith("Put") }?.value
                when {
                    (post is ArraySchema && patch == null && put == null) -> {
                        RipsEntityArraySend(it.key, post.items) as RipsEntity
                    }
                    (post is ObjectSchema || patch is ObjectSchema || put is ObjectSchema) -> {
                        RipsEntitySend(it.key, post, patch, put) as RipsEntity
                    }
                    else -> {
                        logger.error("not implemented scheme: ${it.key}")
                        null
                    }
                }
            }

    val generatedPath = Paths.get("src/generated/java")
    IdHolder.javaFile.writeTo(generatedPath)
    (receiveEntities + sendEntities)
            .forEach { it.build().writeTo(generatedPath) }
}
