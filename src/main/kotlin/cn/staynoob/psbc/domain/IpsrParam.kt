package cn.staynoob.psbc.domain

import cn.staynoob.psbc.util.paramDateFormatter
import java.math.BigDecimal
import java.net.URL
import java.net.URLEncoder
import java.time.LocalDate
import java.time.LocalDateTime

class IpsrParam(
        val TermSsn: String,
        val MercCode: String,
        val OAcqSsn: String,
        val OSttDate: LocalDate,
        val TranAmt: BigDecimal,
        val TermCode: String? = null,
        val TranAbbr: TransAbbr = TransAbbr.IPSR,
        val MercDtTm: LocalDateTime = LocalDateTime.now(),
        val Remark1: String? = null,
        val Remark2: String? = null
) : AbstractParam() {
    // psbc doc says: "退货交易不提供MercUrl方式的结果返回方式,交易结果将返回给原请求地址"
    val MercUrl: URL? = null

    override fun toStringMap(): Map<String, String> {
        val originalMap = super.toStringMap()
        val mercUrl = MercUrl?.let {
            URLEncoder.encode(it.toString(), Charsets.UTF_8.toString())
        } ?: ""

        return originalMap + mapOf(
                "MercUrl" to mercUrl,
                "MercDtTm" to paramDateFormatter.format(MercDtTm)
        )
    }
}