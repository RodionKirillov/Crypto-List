package com.example.cryptolist.search.domain.repository

import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    fun searchCryptocurrency(expression: String): Flow<Resource<List<Cryptocurrency>>>
}