package cn.staynoob.psbc

interface PsbcSignatureService {
    fun sign(plain: String): String
    fun verify(plain: String, signature: String): Boolean
}