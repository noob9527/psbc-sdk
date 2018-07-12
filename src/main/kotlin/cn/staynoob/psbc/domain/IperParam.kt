package cn.staynoob.psbc.domain

import cn.staynoob.psbc.util.paramDatetimeFormatter
import java.math.BigDecimal
import java.net.URLEncoder
import java.time.LocalDate
import java.time.LocalDateTime

class IperParam(
        val TermSsn: String,
        val MercCode: String,
        val TranAmt: BigDecimal,
        val MercUrl: String? = null,
        val TermCode: String? = null,
        val TranAbbr: TransAbbr = TransAbbr.IPER,
        val LimitTime: Int? = null, //单位为分钟 默认为1440
        val MercDtTm: LocalDateTime = LocalDateTime.now(),
        val OAcqSsn: String? = null,
        val OSttDate: LocalDate? = null,
        val Remark1: String? = null,
        val Remark2: String? = null
) : AbstractParam() {

    override fun toStringMap(): Map<String, String> {
        val originalMap = super.toStringMap()

        val mercUrl = MercUrl?.let {
            URLEncoder.encode(it, Charsets.UTF_8.toString())
        } ?: ""

        return originalMap + mapOf(
                "MercUrl" to mercUrl,
                "MercDtTm" to paramDatetimeFormatter.format(MercDtTm)
        )
    }
}

