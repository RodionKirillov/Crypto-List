package com.example.cryptolist.search.data.impl

import com.example.cryptolist.search.data.api.CONNECTION_ERROR
import com.example.cryptolist.search.data.api.ERROR_404
import com.example.cryptolist.search.data.api.INCORRECT_REQUEST
import com.example.cryptolist.search.data.api.SUCCESS
import com.example.cryptolist.search.data.dto.CryptocurrencyListResponse
import com.example.cryptolist.search.data.dto.CryptocurrencySearchRequest
import com.example.cryptolist.search.data.mapper.SearchDtoMapper
import com.example.cryptolist.search.data.source.NetworkClient
import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import com.example.cryptolist.search.domain.repository.CryptoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoListRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: SearchDtoMapper
) : CryptoListRepository {

    override fun searchCryptocurrency(
        expression: String
    ): Flow<CryptoListRequestResult> = flow {
        val response = networkClient.doRequest(CryptocurrencySearchRequest(expression))
        when (response.resultCode) {

            SUCCESS -> {
                emit(
                    CryptoListRequestResult.Content(
                        mapper.map(
                            (response as CryptocurrencyListResponse).cryptocurrencyList,
                            expression
                        )
                    )
                )
            }

            ERROR_404 -> {
                emit(CryptoListRequestResult.Error)
            }

            INCORRECT_REQUEST -> {
                emit(CryptoListRequestResult.Error)
            }

            CONNECTION_ERROR -> {
                emit(CryptoListRequestResult.Error)
            }

            else -> {
                emit(CryptoListRequestResult.Error)
            }
        }
    }
}