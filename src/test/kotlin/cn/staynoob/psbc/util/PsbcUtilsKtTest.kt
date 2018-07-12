package cn.staynoob.psbc.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class PsbcUtilsKtTest {
    @Test
    @DisplayName("test formatter")
    fun test100() {
        val date = LocalDateTime.of(2010, Month.JANUARY, 1, 1, 1, 1)
        val res = paramDatetimeFormatter.format(date)
        assertThat(res.length).isEqualTo("yyyyMMddHHmmss".length)
        assertThat(res).isEqualTo("20100101010101")
    }
}