package cn.staynoob.psbc

import cn.staynoob.psbc.domain.IperParam
import cn.staynoob.psbc.domain.IpsrParam
import cn.staynoob.psbc.domain.WperParam

interface PsbcService {
    fun iper(param: IperParam): String

    fun wper(param: WperParam): String

    fun ipsr(param: IpsrParam): String
}