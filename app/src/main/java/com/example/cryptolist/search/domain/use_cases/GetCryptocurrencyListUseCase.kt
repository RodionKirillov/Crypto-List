package com.example.cryptolist.search.domain.use_cases

import com.example.cryptolist.search.domain.repository.CryptoListRepository

class GetCryptocurrencyListUseCase(
    private val repository: CryptoListRepository
) {
    operator fun invoke(expression: String) = repository.searchCryptocurrency(expression)
}