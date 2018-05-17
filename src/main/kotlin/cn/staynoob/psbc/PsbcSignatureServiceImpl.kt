package cn.staynoob.psbc

import com.psbc.payment.client.core.KeyProvider
import com.psbc.payment.client.core.Signature
import com.sun.net.ssl.internal.ssl.Provider
import java.security.Security

class PsbcSignatureServiceImpl(
        private val properties: PsbcProperties
) : PsbcSignatureService {

    companion object {
        init {
            Security.addProvider(Provider())
        }

        private val signatureInstance = Signature()
    }

    private val keyProvider = KeyProvider(KeyProvider.getInstance(
            properties.jksFile,
            properties.jksPassword
    ))
    private val privateKey = keyProvider.getPrivateKey(
            properties.merchantKeyAlias,
            properties.merchantKeyPassword
    )
    private val certificate = keyProvider.getCertificate(properties.paygateKeyAlias)

    override fun sign(plain: String): String {
        return signatureInstance.sign(plain, privateKey)
    }

    override fun verify(plain: String, signature: String): Boolean {
        return signatureInstance.verify(plain, signature, certificate.publicKey)
    }
}