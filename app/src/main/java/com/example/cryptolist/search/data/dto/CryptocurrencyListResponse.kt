package com.example.cryptolist.search.data.dto

data class CryptocurrencyListResponse(
    val cryptocurrencyList: List<CryptocurrencyItem>
): Response()
