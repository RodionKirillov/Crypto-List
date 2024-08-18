package com.example.cryptolist.search.domain.use_cases

import com.example.cryptolist.search.domain.repository.CryptoRepository

class GetCryptocurrencyListUseCase(
    private val repository: CryptoRepository
) {
    operator fun invoke(expression: String) = repository.searchCryptocurrency(expression)
}