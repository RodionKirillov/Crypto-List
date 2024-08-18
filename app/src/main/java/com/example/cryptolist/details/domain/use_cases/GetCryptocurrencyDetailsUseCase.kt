package com.example.cryptolist.details.domain.use_cases

import com.example.cryptolist.details.domain.repository.CryptoDetailsRepository

class GetCryptocurrencyDetailsUseCase(
    private val repository: CryptoDetailsRepository
) {
    operator fun invoke(expression: String) = repository.getCryptocurrencyDetails(expression)
}