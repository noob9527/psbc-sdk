package cn.staynoob.psbc

import cn.staynoob.psbc.util.appendParam
import cn.staynoob.psbc.util.iterator
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

class PsbcHttpInterceptor(
        private val signatureService: PsbcSignatureService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        if (original.method() != "POST") return chain.proceed(original)
        val originalBody = original.body() as? FormBody
                ?: return chain.proceed(original)
        val map = originalBody.iterator()
                .asSequence()
                .toMap()

        val plain = map["Plain"] ?: return chain.proceed(original)
        val fingerPrint = signatureService.sign(plain)

        val newBody = originalBody.appendParam(mapOf("Signature" to fingerPrint))

        val newRequest = original.newBuilder()
                .method(original.method(), newBody)
                .build()

        return chain.proceed(newRequest)
    }
}