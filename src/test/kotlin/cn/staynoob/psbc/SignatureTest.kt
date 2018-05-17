package cn.staynoob.psbc

import com.psbc.payment.client.core.Signature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.security.KeyPairGenerator

class SignatureTest {

    private val signature = Signature()
    private val plain = "12345"
    private val keyGen = KeyPairGenerator.getInstance("RSA")
    private val keyPair = keyGen.genKeyPair()

    @Test
    @DisplayName("test100")
    fun test100() {
        val fingerPrint = signature.sign(plain, keyPair.private)
        val res = signature.verify(plain, fingerPrint, keyPair.public)
        assertThat(res).isTrue()
    }
}