package cn.staynoob.psbc

import cn.staynoob.psbc.kotlin.KotlinAllOpen
import cn.staynoob.psbc.kotlin.NoArgConstructor
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.annotation.PostConstruct

@Validated
@KotlinAllOpen
@NoArgConstructor
@ConfigurationProperties(prefix = "psbc")
class PsbcProperties(
        var jksFile: String,
        var jksPassword: String,
        var merchantKeyAlias: String,
        var merchantKeyPassword: String,
        var paygateKeyAlias: String,
        @get: NotNull
        var mercCode: String,
        var callbackServer: String?,
        var apiUrl: String,
        var logLevel: HttpLoggingInterceptor.Level
) {

    /**
     * somehow kotlin init logic dose not execute
     */
    @Suppress("SENSELESS_COMPARISON")
    @PostConstruct
    private fun init() {
        if (jksFile == null) jksFile = "psbc_merchant.jks"
        if (jksPassword == null) jksPassword = "111111"
        if (merchantKeyAlias == null) merchantKeyAlias = "merchant_key"
        if (merchantKeyPassword == null) merchantKeyPassword = "111111"
        if (paygateKeyAlias == null) paygateKeyAlias = "paygate_cert"
        if (apiUrl == null) apiUrl = "http://103.22.255.201:8443/psbcpay/main/"
        if (logLevel == null) logLevel = HttpLoggingInterceptor.Level.NONE
    }
}