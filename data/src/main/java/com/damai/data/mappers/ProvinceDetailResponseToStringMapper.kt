package com.damai.data.mappers

import com.damai.base.BaseMapper
import com.damai.data.response.ProvinceResponse

/**
 * Created by damai007 on 04/July/2023
 */
class ProvinceDetailResponseToStringMapper : BaseMapper<ProvinceResponse.ProvinceDetail, String>() {

    override fun map(value: ProvinceResponse.ProvinceDetail): String {
        return value.name.orEmpty()
    }
}