package com.example.cryptolist.search.data.mapper

import com.example.cryptolist.details.domain.model.CryptocurrencyDetails
import com.example.cryptolist.search.data.dto.CryptocurrencyDto
import com.example.cryptolist.search.data.dto.RUB_CURRENCY_DTO
import com.example.cryptolist.search.data.dto.USD_CURRENCY_DTO
import com.example.cryptolist.search.data.dto.details_dto.CryptocurrencyDetailsResponse
import com.example.cryptolist.search.domain.model.Cryptocurrency
import java.text.NumberFormat
import java.util.Locale

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

    fun map(cryptoDetailsDto: CryptocurrencyDetailsResponse): CryptocurrencyDetails {
        return CryptocurrencyDetails(
            id = cryptoDetailsDto.id,
            image = cryptoDetailsDto.image.large,
            description = cryptoDetailsDto.description.en,
            categories = cryptoDetailsDto.categories
        )
    }

    private fun currencyUTF(request: String, currentPrice: Double): String {
        return when (request) {
            RUB_CURRENCY_DTO -> {
                "\u20BD ".plus(formatNumber(currentPrice, request))
            }

            USD_CURRENCY_DTO -> {
                "\u0024 ".plus(formatNumber(currentPrice, request))
            }

            else -> UNKNOWN_CURRENCY_DTO
        }
    }

    private fun formatNumber(value: Double, request: String): String {
        return when (request) {
            USD_CURRENCY_DTO -> {
                val numberFormat = NumberFormat.getNumberInstance(Locale.US)
                numberFormat.format(value)
            }

            else -> {
                val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
                numberFormat.format(value)
            }
        }
    }

    companion object {

        const val UNKNOWN_CURRENCY_DTO = ""
    }
}