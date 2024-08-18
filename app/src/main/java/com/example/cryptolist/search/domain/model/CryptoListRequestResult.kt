package com.example.cryptolist.search.domain.model

sealed interface CryptoListRequestResult {

    data class Content(
        val cryptocurrencies: List<Cryptocurrency>
    ) : CryptoListRequestResult

    object Error : CryptoListRequestResult
}