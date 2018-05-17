package cn.staynoob.psbc.domain

enum class TransAbbr(val description: String) {
    IPER("个人客户支付"),
    WPER("个人客户手机支付"),
    IPSR("退货"),

    ;
}