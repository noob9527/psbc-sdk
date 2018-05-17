package cn.staynoob.psbc.integration

import cn.staynoob.psbc.PsbcService
import cn.staynoob.psbc.autoconfigure.PsbcAutoConfiguration
import cn.staynoob.psbc.domain.IperParam
import cn.staynoob.psbc.domain.IpsrParam
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import java.time.LocalDate

@ImportAutoConfiguration(PsbcAutoConfiguration::class)
class PsbcServiceIt : IntegrationTestBase() {

    @Autowired
    private lateinit var service: PsbcService

    @Value("\${psbc-test-merc-code}")
    private lateinit var testMercCode: String

    @Test
    @DisplayName("iperTest100")
    fun iperTest100() {
        val param = IperParam(
                TermSsn = "testIperSsn1",
                MercCode = testMercCode,
                TranAmt = 10.5.toBigDecimal()
        )
        val res = service.iper(param)
        println(res)
    }

    @Test
    @DisplayName("ipsrTest100")
    fun ipsrTest100() {
        val param = IpsrParam(
                "testIpsrSsn1",
                testMercCode,
                "testIperSsn1",
                LocalDate.now(),
                10.5.toBigDecimal()
        )
        val res = service.ipsr(param)
        println(res)
    }
}