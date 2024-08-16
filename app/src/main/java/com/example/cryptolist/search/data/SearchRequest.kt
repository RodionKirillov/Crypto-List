package com.example.cryptolist.search.data

interface SearchRequest {

    data class Usd(
        val value: String = "usd"
    ) : SearchRequest

    data class Rub(
        val value: String = "rub"
    ) : SearchRequest
}