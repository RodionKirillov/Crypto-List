package com.example.cryptolist.details.domain.model

data class CryptocurrencyDetails(
    val id: String,
    val image: String,
    val description: String,
    val categories: List<String>
)
