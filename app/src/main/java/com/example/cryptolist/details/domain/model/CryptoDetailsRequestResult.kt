package com.example.cryptolist.details.domain.model

sealed interface CryptoDetailsRequestResult {

    data class Content(
        val details: CryptocurrencyDetails
    ) : CryptoDetailsRequestResult

    object Error : CryptoDetailsRequestResult
}