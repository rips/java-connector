package com.ripstech.api.entity.generator

import com.squareup.javapoet.*

data class GuessedType(val typeName: TypeName, val initializer: CodeBlock? = null, val innerClass: TypeSpec? = null) {

    constructor() : this(ClassName.OBJECT)

    constructor(javaClass: Class<*>) : this(ClassName.get(javaClass))

}