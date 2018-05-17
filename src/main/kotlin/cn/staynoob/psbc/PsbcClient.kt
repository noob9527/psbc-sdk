package cn.staynoob.psbc

import cn.staynoob.psbc.domain.TransAbbr
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PsbcClient {
    @POST(".")
    @FormUrlEncoded
    fun iper(
            @Field(value = "Plain", encoded = true) plain: String,
            @Field("transName") transName: String = TransAbbr.IPER.name
    ): Call<String>

    @POST(".")
    @FormUrlEncoded
    fun wper(
            @Field(value = "Plain", encoded = true) plain: String,
            @Field("transName") transName: String = TransAbbr.WPER.name
    ): Call<String>

    @POST(".")
    @FormUrlEncoded
    fun ipsr(
            @Field(value = "Plain", encoded = true) plain: String,
            @Field("transName") transName: String = TransAbbr.IPSR.name
    ): Call<String>
}