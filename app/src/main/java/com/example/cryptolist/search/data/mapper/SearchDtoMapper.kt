package com.example.cryptolist.search.data.mapper

import com.example.cryptolist.search.data.dto.CryptocurrencyDto
import com.example.cryptolist.search.domain.model.Cryptocurrency

class SearchDtoMapper {

    fun mapCryptocurrencyFromCryptocurrencyDto(
        cryptocurrencies: List<CryptocurrencyDto>
    ): List<Cryptocurrency> {
        return cryptocurrencies.map {
            Cryptocurrency(
                id = it.id,
                image = it.image,
                name = it.name,
                symbol = it.symbol,
                currentPrice = it.currentPrice,
                priceChangePercentage24h = it.priceChangePercentage24h
            )
        }
    }
}