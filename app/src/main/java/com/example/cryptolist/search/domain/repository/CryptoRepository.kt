package com.example.cryptolist.search.domain.repository

import com.example.cryptolist.search.domain.model.RequestResult
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun searchCryptocurrency(expression: String): Flow<RequestResult>
}