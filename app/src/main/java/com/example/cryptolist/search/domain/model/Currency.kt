package com.example.cryptolist.search.domain.model

sealed class Currency(open val value: String) {

    data class UsdCurrency(
        override val value: String = "usd"
    ): Currency(value)

    data class RubCurrency(
        override val value: String = "rub"
    ): Currency(value)
}