package com.mindstix.network.data

import com.google.gson.annotations.SerializedName

/**
 * This class contains the data model for coins
 */
data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)