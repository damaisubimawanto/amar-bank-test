package com.damai.base.network

/**
 * Created by damai007 on 04/July/2023
 */
sealed class Resource<out T> {

    data class Success<T>(val model: T? = null) : Resource<T>()

    data class Error(val errorMessage: String?) : Resource<Nothing>()
}
