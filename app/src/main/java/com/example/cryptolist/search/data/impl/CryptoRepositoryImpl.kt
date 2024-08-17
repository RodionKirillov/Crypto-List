package com.example.cryptolist.search.data.impl

import com.example.cryptolist.search.data.api.CONNECTION_ERROR
import com.example.cryptolist.search.data.api.ERROR_404
import com.example.cryptolist.search.data.api.INCORRECT_REQUEST
import com.example.cryptolist.search.data.api.SUCCESS
import com.example.cryptolist.search.data.source.NetworkClient
import com.example.cryptolist.search.data.dto.CryptocurrencyListResponse
import com.example.cryptolist.search.data.dto.CryptocurrencySearchRequest
import com.example.cryptolist.search.data.mapper.SearchDtoMapper
import com.example.cryptolist.search.domain.model.RequestResult
import com.example.cryptolist.search.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: SearchDtoMapper
) : CryptoRepository {

    override fun searchCryptocurrency(
        expression: String
    ): Flow<RequestResult> = flow {
        val response = networkClient.doRequest(CryptocurrencySearchRequest(expression))
        when (response.resultCode) {

            SUCCESS -> {
                emit(
                    RequestResult.RequestContent(
                        mapper.map(
                            (response as CryptocurrencyListResponse).cryptocurrencyList,
                            expression
                        )
                    )
                )
            }

            ERROR_404 -> {
                emit(RequestResult.Error)
            }

            INCORRECT_REQUEST -> {
                emit(RequestResult.Error)
            }

            CONNECTION_ERROR -> {
                emit(RequestResult.Error)
            }

            else -> {
                emit(RequestResult.Error)
            }
        }
    }
}