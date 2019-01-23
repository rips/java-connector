package com.ripstech.api.entity.generator

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import io.swagger.v3.oas.models.media.*
import org.apache.logging.log4j.kotlin.Logging
import java.math.BigInteger
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

abstract class TypeGuessing: Logging {

    protected val emptyList = CodeBlock.of("\$T.\$N()", ClassName.get(Collections::class.java), "emptyList")!!
    protected val emptyMap = CodeBlock.of("\$T.\$N()", ClassName.get(Collections::class.java), "emptyMap")!!
    protected val emptySet = CodeBlock.of("\$T.\$N()", ClassName.get(Collections::class.java), "emptySet")!!

    fun guess(name: String, schema: IntegerSchema): GuessedType {
        return when(schema.format) {
            "int32" -> GuessedType(ClassName.INT.box())
            "int64" -> GuessedType(ClassName.LONG.box())
            "bigint" -> GuessedType(BigInteger::class.java)
            else -> {
                logger.warn("No format specified, guess integer type via name ($name) or use Integer")
                when {
                    name == "id" -> GuessedType(ClassName.LONG)
                    name.contains("""application|scan|issue|file|function|class|type|user|team|organisation|chargedQuota|upload""".toRegex(RegexOption.IGNORE_CASE)) -> GuessedType(ClassName.LONG.box())
                    else -> GuessedType(ClassName.INT.box())
                }
            }
        }
    }

    fun guess(@Suppress("UNUSED_PARAMETER") name: String, @Suppress("UNUSED_PARAMETER") schema: BooleanSchema): GuessedType {
        return GuessedType(ClassName.BOOLEAN.box())
    }

    fun guess(name: String, schema: ArraySchema, parentTypeName: ClassName): GuessedType {
        val innerType = guess(name.dropLastWhile { it == 's' }, schema.items, parentTypeName)
        return when (innerType.typeName) {
            TypeName.OBJECT -> {
                logger.error("Type Object in $parentTypeName::$name (List)")
                GuessedType(ParameterizedTypeName.get(ClassName.get(List::class.java), TypeName.OBJECT), emptyList)
            }
            else -> {
                GuessedType(ParameterizedTypeName.get(ClassName.get(List::class.java), innerType.typeName), emptyList, innerType.innerClass)
            }
        }
    }

    fun guess(name: String, schema: MapSchema): GuessedType {
        return GuessedType(ParameterizedTypeName.get(ClassName.get(Map::class.java), ClassName.get(String::class.java),
                guess(name, schema.additionalProperties as Schema<*>).typeName),
                emptyMap)
    }

    fun guess(name: String, schema: StringSchema): GuessedType {
        return when(name) {
            "created_at", "published_at", "valid_until", "validUntil", "valid_from", "validFrom", "last_login", "submission", "start", "finish",
            "created" -> {
                logger.warn("Select DateTimeSchema via name ($name)")
                guess(name, DateTimeSchema())
            }
            else -> GuessedType(String::class.java)
        }
    }

    fun guess(name: String, schema: DateTimeSchema): GuessedType {
        return GuessedType(OffsetDateTime::class.java)
    }

    fun guess(name: String, schema: DateSchema): GuessedType {
        return GuessedType(LocalDate::class.java)
    }

    fun guess(name: String, schema: EmailSchema): GuessedType {
        return GuessedType(String::class.java)
    }

    abstract fun guess(name: String, schema: ObjectSchema, parentTypeName: ClassName): GuessedType

    open fun guess(name: String, schema: Schema<*>, parentTypeName: ClassName = ClassName.OBJECT): GuessedType {
        if(schema.`$ref` != null) { //TODO lookup
            val (packageName, entityName) = ClassNaming.naming(schema.`$ref`.replace("#/components/schemas/", ""))
            return GuessedType(ClassName.get(
                    sequenceOf("com.ripstech.api.entity", "receive", packageName) //TODO refs only work for receive package
                            .filter { it.isNotBlank() }
                            .joinToString("."),
                    entityName))
        }
        return when (schema) {
            is IntegerSchema -> guess(name, schema)
            is BooleanSchema -> guess(name, schema)
            is ArraySchema -> guess(name, schema, parentTypeName)
            is MapSchema -> guess(name, schema)
            is StringSchema -> guess(name, schema)
            is DateTimeSchema -> guess(name, schema)
            is DateSchema -> guess(name, schema)
            is EmailSchema -> guess(name, schema)
            is ObjectSchema -> guess(name, schema, parentTypeName)
            else -> {
                logger.error("$name in $parentTypeName (no schema/ref) used Void")
                GuessedType(TypeName.VOID.box())
            }
        }
    }

}
