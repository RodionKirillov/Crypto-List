package com.example.cryptolist.search.domain.interactor

import com.example.cryptolist.search.domain.model.Cryptocurrency
import kotlinx.coroutines.flow.Flow

interface CryptocurrencyInteractor {

    fun searchCryptocurrency(expression: String): Flow<Pair<List<Cryptocurrency>?, String?>>
}