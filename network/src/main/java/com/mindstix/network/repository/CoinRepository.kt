package com.mindstix.network.repository

import com.mindstix.network.data.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

}