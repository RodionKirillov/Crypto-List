package com.example.cryptolist.search.domain.repository

import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import kotlinx.coroutines.flow.Flow

interface CryptoListRepository {

    fun searchCryptocurrency(expression: String): Flow<CryptoListRequestResult>
}