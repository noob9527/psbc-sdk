package cn.staynoob.psbc.autoconfigure

import cn.staynoob.psbc.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@Configuration
@AutoConfigureOrder
@EnableConfigurationProperties(PsbcProperties::class)
class PsbcAutoConfiguration(
        private val properties: PsbcProperties
) {

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply {
                level = properties.logLevel
            }

    companion object {
        private val scalarsConverterFactory = ScalarsConverterFactory.create()
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun psbcService(): PsbcService {
        val client = OkHttpClient.Builder()
                .addInterceptor(PsbcHttpInterceptor(psbcSingatureService()))
                .addInterceptor(loggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(properties.apiUrl)
                .addConverterFactory(scalarsConverterFactory)
                .client(client)
                .build()

        val nsClient = retrofit.create(PsbcClient::class.java)
        return PsbcServiceImpl(nsClient, properties)
    }

    @Bean
    @ConditionalOnMissingBean
    internal fun psbcSingatureService(): PsbcSignatureService {
        return PsbcSignatureServiceImpl(properties)
    }
}