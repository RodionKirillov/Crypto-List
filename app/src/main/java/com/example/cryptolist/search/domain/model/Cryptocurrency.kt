package com.example.cryptolist.search.domain.model

data class Cryptocurrency(
    val id: String,
    val image: String,
    val name: String,
    val symbol: String,
    val currentPrice: String,
    val priceChangePercentage24h: Double,
)
