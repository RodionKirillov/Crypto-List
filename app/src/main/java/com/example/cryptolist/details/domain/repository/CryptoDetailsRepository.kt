package com.example.cryptolist.details.domain.repository

import com.example.cryptolist.search.domain.model.CryptoDetailsRequestResult
import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import kotlinx.coroutines.flow.Flow

interface CryptoDetailsRepository {

    fun getCryptocurrencyDetails(expression: String):Flow<CryptoDetailsRequestResult>
}