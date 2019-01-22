package com.ripstech.api.entity.generator

class ClassNaming {

    companion object {
        fun naming(name: String): FqClassName {
            val parts = name.split(".")
            val packageName = parts.dropLast(1).joinToString(separator = ".") { it.toLowerCase() }
            return FqClassName(packageName, parts.last())
        }
    }

}

data class FqClassName(val packageName: String = "", val name: String)