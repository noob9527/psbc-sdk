package cn.staynoob.psbc.util

import cn.staynoob.psbc.PsbcApiException
import okhttp3.FormBody
import retrofit2.Call
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.reflect.full.memberProperties

internal val paramDateFormatter = DateTimeFormatter
        .ofPattern("yyyyMMddHHmmss")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

internal fun objectToMap(dataObject: Any): Map<String, Any?> {
    return dataObject::class.memberProperties
            .map { it.name to it.getter.call(dataObject) }
            .toMap()
}

internal operator fun FormBody.iterator(): Iterator<Pair<String, String>> {
    return object : Iterator<Pair<String, String>> {
        private var currentIndex = 0
        override fun hasNext(): Boolean {
            return currentIndex < this@iterator.size()
        }

        override fun next(): Pair<String, String> {
            val res = this@iterator.name(currentIndex) to this@iterator.value(currentIndex)
            currentIndex += 1
            return res
        }
    }
}

internal fun FormBody.appendParam(
        param: Map<String, String>
): FormBody {
    val original = iterator().asSequence().toMap()
    val builder = FormBody.Builder()
    (param + original).entries
            .forEach { builder.addEncoded(it.key, it.value) }
    return builder.build()
}

internal fun <T> Call<T>.exec(): T? {
    val res = this.execute()
    if (!res.isSuccessful) {
        val errMsg = res.errorBody()?.string()
        val message = res.message()
        throw PsbcApiException(errMsg ?: message)
    }
    return res.body()
}
