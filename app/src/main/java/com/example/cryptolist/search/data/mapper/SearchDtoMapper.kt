package com.example.cryptolist.search.data.mapper

import com.example.cryptolist.search.data.dto.CryptocurrencyDto
import com.example.cryptolist.search.data.dto.RUB_CURRENCY_DTO
import com.example.cryptolist.search.data.dto.USD_CURRENCY_DTO
import com.example.cryptolist.search.domain.model.Cryptocurrency

class SearchDtoMapper {

    fun map(
        cryptocurrencies: List<CryptocurrencyDto>,
        request: String
    ): List<Cryptocurrency> {
        return cryptocurrencies.map {
            Cryptocurrency(
                id = it.id,
                image = it.image,
                name = it.name,
                symbol = it.symbol,
                currentPrice = currencyUTF(request, it.currentPrice),
                priceChangePercentage24h = it.priceChangePercentage24h
            )
        }
    }

    private fun currencyUTF(request: String, currentPrice: Double): String {
        return when (request) {
            RUB_CURRENCY_DTO -> {"\u20BD ".plus(currentPrice)}
            USD_CURRENCY_DTO -> {"\u0024 ".plus(currentPrice)}
            else -> UNKNOWN_CURRENCY_DTO
        }
    }

    companion object {

        const val UNKNOWN_CURRENCY_DTO = ""
    }
}