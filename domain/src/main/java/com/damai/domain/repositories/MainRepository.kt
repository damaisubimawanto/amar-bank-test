package com.damai.domain.repositories

import com.damai.base.network.Resource
import com.damai.domain.models.ProvinceListModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 04/July/2023
 */
interface MainRepository {

    @Throws(Exception::class)
    fun getProvinceList(): Flow<Resource<ProvinceListModel>>
}