package com.ripstech.api.entity.generator

import com.fasterxml.jackson.annotation.JsonProperty
import com.squareup.javapoet.AnnotationSpec
import org.apache.commons.text.CaseUtils
import javax.lang.model.SourceVersion

class TypeNaming {

    companion object {
        fun name(name: String) : Pair<String, AnnotationSpec?> {
            return when {
                SourceVersion.isKeyword(name) -> (name + "_") to
                        AnnotationSpec.builder(JsonProperty::class.java)
                                .addMember("value", "\$S", name)
                                .build()
                else -> (if(name.contains("_")) CaseUtils.toCamelCase(name, false, '_') else name) to null
            }
        }
    }

}