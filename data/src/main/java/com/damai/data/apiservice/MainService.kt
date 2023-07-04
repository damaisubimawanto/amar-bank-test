package com.damai.data.apiservice

import com.damai.data.response.ProvinceResponse
import retrofit2.http.GET

/**
 * Created by damai007 on 04/July/2023
 */
interface MainService {

    @GET("/v1/regional/provinsi?api_key=0sMHu30JGXn4WT9Yt9Cl6e82TYlar3")
    suspend fun getProvinceList(): ProvinceResponse
}