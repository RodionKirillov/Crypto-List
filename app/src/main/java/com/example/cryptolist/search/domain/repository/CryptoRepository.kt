package com.example.cryptolist.search.domain.repository

import com.example.cryptolist.search.domain.model.CryptoDetailsRequestResult
import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun searchCryptocurrency(expression: String): Flow<CryptoListRequestResult>

    fun getCryptocurrencyDetails(expression: String):Flow<CryptoDetailsRequestResult>
}