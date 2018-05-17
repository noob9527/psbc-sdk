package cn.staynoob.psbc

import cn.staynoob.psbc.autoconfigure.PsbcAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(initializers = [ConfigFileApplicationContextInitializer::class])
@ActiveProfiles("default", "test-integration")
@ImportAutoConfiguration(PsbcAutoConfiguration::class)
class PsbcSignatureServiceImplTest {

    private val plain = "12345"

    @Autowired
    private lateinit var service: PsbcSignatureService

    @Test
    @DisplayName("test100")
    fun test100() {
        val fingerPrint = service.sign(plain)
        val res = service.verify(plain, fingerPrint)
        assertThat(res).isTrue()
    }
}