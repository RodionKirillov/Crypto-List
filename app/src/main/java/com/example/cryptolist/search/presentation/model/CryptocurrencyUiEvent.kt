package com.example.cryptolist.search.presentation.model

sealed interface CryptocurrencyUiEvent {

    object UsdCurrencyClick : CryptocurrencyUiEvent

    object RubCurrencyClick : CryptocurrencyUiEvent
}