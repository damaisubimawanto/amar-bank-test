package com.damai.domain.usecases

import com.damai.base.network.FlowUseCase
import com.damai.base.network.Resource
import com.damai.domain.models.ProvinceListModel
import com.damai.domain.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by damai007 on 04/July/2023
 */
class ProvinceUseCase @Inject constructor(
    private val mainRepository: MainRepository
): FlowUseCase<Nothing?, ProvinceListModel>() {

    override suspend fun execute(parameters: Nothing?): Flow<Resource<ProvinceListModel>> {
        return mainRepository.getProvinceList()
    }
}