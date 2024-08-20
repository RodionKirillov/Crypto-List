package com.example.cryptolist.details.domain.repository

import com.example.cryptolist.details.domain.model.CryptoDetailsRequestResult
import kotlinx.coroutines.flow.Flow

interface CryptoDetailsRepository {

    fun getCryptocurrencyDetails(expression: String):Flow<CryptoDetailsRequestResult>
}