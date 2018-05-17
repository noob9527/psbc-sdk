package cn.staynoob.psbc

import cn.staynoob.psbc.domain.IperParam
import cn.staynoob.psbc.domain.IpsrParam

interface PsbcService {
    fun iper(param: IperParam): String

    fun ipsr(param: IpsrParam): String
}