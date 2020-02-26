package com.ripstech.api.entity.generator

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec

data class GuessedType(val typeName: TypeName, val initializer: CodeBlock? = null, val innerClass: TypeSpec? = null) {

    constructor() : this(ClassName.OBJECT)

    constructor(javaClass: Class<*>) : this(ClassName.get(javaClass))
}
