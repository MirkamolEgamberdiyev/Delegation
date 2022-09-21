package com.mirkamol.delegation.network

import com.mirkamol.delegation.model.TVShowPopular
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TVshowService {
    @GET("api/most-popular")
    suspend fun apiTVshowPopular(@Query("page")page:Int): Flow<TVShowPopular>

    @GET("api/most-popular")
    suspend fun apiTVshowPopular2(@Query("page") page:Int): Response<TVShowPopular>

}