package com.example.cryptolist.search.domain.model

sealed interface RequestResult {

    data class RequestContent(
        val cryptocurrencies: List<Cryptocurrency>
    ) : RequestResult

    object Error : RequestResult
}