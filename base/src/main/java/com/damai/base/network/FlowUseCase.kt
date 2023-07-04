package com.damai.base.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Created by damai007 on 04/July/2023
 */
abstract class FlowUseCase<in P, R> {

    suspend operator fun invoke(parameters: P? = null): Flow<Resource<R>> = execute(parameters)
        .catch { e ->
            Resource.Error(errorMessage = e.localizedMessage)
        }

    protected abstract suspend fun execute(parameters: P? = null): Flow<Resource<R>>
}