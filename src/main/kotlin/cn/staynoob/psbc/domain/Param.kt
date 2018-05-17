package cn.staynoob.psbc.domain

import cn.staynoob.psbc.util.objectToMap

abstract class AbstractParam {

    open fun toMap(): Map<String, Any?> {
        return objectToMap(this)
    }

    open fun toStringMap(): Map<String, String> {
        val res = toMap().mapValues { it.value?.toString() ?: "" }
        return res
    }

    open fun toPlain(): String {
        val res = toStringMap()
                .map { "${it.key}=${it.value}" }
                .joinToString("|")
        return res
    }
}