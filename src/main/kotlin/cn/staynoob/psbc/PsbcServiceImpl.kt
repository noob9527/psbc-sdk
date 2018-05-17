package cn.staynoob.psbc

import cn.staynoob.psbc.domain.IperParam
import cn.staynoob.psbc.domain.IpsrParam
import cn.staynoob.psbc.util.exec

class PsbcServiceImpl(
        private val client: PsbcClient,
        private val properties: PsbcProperties
) : PsbcService {
    override fun iper(param: IperParam): String {
        return client.iper(param.toPlain()).exec()!!
    }

    override fun ipsr(param: IpsrParam): String {
        return client.ipsr(param.toPlain()).exec()!!
    }
}