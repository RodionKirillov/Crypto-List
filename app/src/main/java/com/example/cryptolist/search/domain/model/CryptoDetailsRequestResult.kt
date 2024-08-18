package com.example.cryptolist.search.domain.model

import com.example.cryptolist.details.domain.model.CryptocurrencyDetails

sealed interface CryptoDetailsRequestResult {

    data class Content(
        val details: CryptocurrencyDetails
    ) : CryptoDetailsRequestResult

    object Error : CryptoDetailsRequestResult
}