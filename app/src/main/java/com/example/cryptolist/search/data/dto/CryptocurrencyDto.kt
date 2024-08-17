package com.example.cryptolist.search.data.dto

import com.google.gson.annotations.SerializedName

data class CryptocurrencyDto(
    val ath: Double,
    val athChangePercentage: Double,
    val athDate: String,
    val atl: Double,
    val atlChangePercentage: Double,
    val atlDate: String,
    val circulatingSupply: Int,
    @SerializedName("current_price") val currentPrice: Double,
    val fullyDilutedValuation: Long,
    val high24h: Double,
    val id: String,
    val image: String,
    val lastUpdated: String,
    val low24h: Double,
    val marketCap: Long,
    val marketCapChange24h: Long,
    val marketCapChangePercentage24h: Double,
    val marketCapRank: Double,
    val maxSupply: Double,
    val name: String,
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24h: Double,
    val roi: Any,
    val symbol: String,
    val totalSupply: Double,
    val totalVolume: Long
)