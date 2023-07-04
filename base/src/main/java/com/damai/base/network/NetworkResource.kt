package com.damai.base.network

import com.damai.base.BaseModel
import com.damai.base.coroutines.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Created by damai007 on 04/July/2023
 */
abstract class NetworkResource<T: BaseModel>(
    private val dispatcherProvider: DispatcherProvider
) {

    fun asFlow(): Flow<Resource<T>> = flow {
        if (shouldFetchFromRemote()) {
            val remoteResponse = safeApiCall(dispatcher = dispatcherProvider.io()) {
                remoteFetch()
            }

            when (remoteResponse) {
                is ResultWrapper.Success -> {
                    emit(Resource.Success(model = remoteResponse.value))
                }
                is ResultWrapper.GenericError -> {
                    emit(Resource.Error(errorMessage = remoteResponse.message))
                }
            }
        } else {
            /* Get from cache. */
            val localCache = withContext(dispatcherProvider.io()) {
                localFetch()
            }
            emit(Resource.Success(model = localCache))
        }
    }

    abstract suspend fun remoteFetch(): T

    open suspend fun localFetch(): T? = null

    open fun shouldFetchFromRemote() = true
}