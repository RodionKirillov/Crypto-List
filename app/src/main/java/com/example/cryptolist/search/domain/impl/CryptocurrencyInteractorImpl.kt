package com.example.cryptolist.search.domain.impl

import com.example.cryptolist.search.domain.interactor.CryptocurrencyInteractor
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.domain.repository.CryptoRepository
import com.example.cryptolist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptocurrencyInteractorImpl(
    private val repository: CryptoRepository
) : CryptocurrencyInteractor {

    override fun searchCryptocurrency(
        expression: String
    ): Flow<Pair<List<Cryptocurrency>?, String?>> {
        return repository.searchCryptocurrency(expression).map { result ->
            when (result) {

                is Resource.Success -> {
                    Pair(result.data, null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }

            }
        }
    }
}