package com.mindstix.network.api

import com.mindstix.network.data.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

}