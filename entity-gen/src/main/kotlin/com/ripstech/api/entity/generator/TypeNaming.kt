package com.ripstech.api.entity.generator

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.javapoet.AnnotationSpec
import javax.lang.model.SourceVersion
import org.apache.commons.text.CaseUtils

class TypeNaming {

    companion object {
        fun name(name: String): Pair<String, AnnotationSpec?> {
            return when {
                SourceVersion.isKeyword(name) -> (name + "_") to
                        AnnotationSpec.builder(JsonProperty::class.java)
                                .addMember("value", "\$S", name)
                                .build()
                else -> (if (name.contains("_")) CaseUtils.toCamelCase(name, false, '_') else name) to null
            }
        }
    }
}
