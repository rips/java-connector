package com.ripstech.api.entity.generator

import com.ripstech.api.entity.IdHolder
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeSpec
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.ObjectSchema

class TypeGuessingCommon: TypeGuessing() {

    override fun guess(name: String, schema: ObjectSchema, parentTypeName: ClassName): GuessedType {
        if(schema.properties.size == 1 && schema.properties.toList()[0].second is IntegerSchema) {
            return GuessedType(IdHolder.className)
        }
        return if(schema.properties.isNotEmpty()) {
            schema.properties.mapValues { guess(it.key, it.value) }
            val nestedClassName = parentTypeName.nestedClass(name.capitalize())
            val nestedClass = TypeSpec.classBuilder(nestedClassName).build()

            GuessedType(nestedClassName, innerClass = nestedClass)
        } else {
            logger.error("Type Object in $parentTypeName::$name")
            GuessedType()
        }
    }

}