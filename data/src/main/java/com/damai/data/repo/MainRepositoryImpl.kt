package com.damai.data.repo

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.network.NetworkResource
import com.damai.base.network.Resource
import com.damai.data.apiservice.MainService
import com.damai.data.mappers.ProvinceDetailResponseToStringMapper
import com.damai.domain.models.ProvinceListModel
import com.damai.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by damai007 on 04/July/2023
 */
class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val dispatcherProvider: DispatcherProvider,
    private val provinceDetailMapper: ProvinceDetailResponseToStringMapper
) : MainRepository {

    override fun getProvinceList(): Flow<Resource<ProvinceListModel>> {
        return object : NetworkResource<ProvinceListModel>(
            dispatcherProvider = dispatcherProvider
        ) {
            override suspend fun remoteFetch(): ProvinceListModel {
                val response = mainService.getProvinceList()
                return ProvinceListModel(
                    data = response.data?.let {
                        provinceDetailMapper.map(it).filter { provinceName ->
                            provinceName.isNotEmpty()
                        }
                    }
                )
            }
        }.asFlow()
    }
}