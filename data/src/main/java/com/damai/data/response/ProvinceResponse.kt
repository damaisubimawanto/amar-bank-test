package com.damai.data.response

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 04/July/2023
 */
class ProvinceResponse : BaseResponse() {
    @SerializedName("data")
    val data: List<ProvinceDetail>? = null

    class ProvinceDetail {
        @SerializedName("id")
        val id: String? = null

        @SerializedName("name")
        val name: String? = null
    }
}