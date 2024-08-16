package com.example.cryptolist.search.presentation

import com.example.cryptolist.search.domain.model.Cryptocurrency

sealed interface CryptocurrenciesSate {

    data class Content(
        val cryptocurrencies: List<Cryptocurrency>
    ) : CryptocurrenciesSate

    object Error : CryptocurrenciesSate

    object Loading : CryptocurrenciesSate
}