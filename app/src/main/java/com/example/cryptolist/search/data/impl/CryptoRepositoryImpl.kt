package com.example.cryptolist.search.data.impl

import com.example.cryptolist.search.data.api.CONNECTION_ERROR
import com.example.cryptolist.search.data.api.ERROR_404
import com.example.cryptolist.search.data.api.INCORRECT_REQUEST
import com.example.cryptolist.search.data.api.SUCCESS
import com.example.cryptolist.search.data.dto.CryptocurrencyDetailsSearchRequest
import com.example.cryptolist.search.data.dto.CryptocurrencyListResponse
import com.example.cryptolist.search.data.dto.CryptocurrencySearchRequest
import com.example.cryptolist.search.data.dto.details_dto.CryptocurrencyDetailsResponse
import com.example.cryptolist.search.data.mapper.SearchDtoMapper
import com.example.cryptolist.search.data.source.NetworkClient
import com.example.cryptolist.search.domain.model.CryptoDetailsRequestResult
import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import com.example.cryptolist.search.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: SearchDtoMapper
) : CryptoRepository {

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

    override fun getCryptocurrencyDetails(
        expression: String
    ): Flow<CryptoDetailsRequestResult> = flow {
        val response = networkClient.doRequest(CryptocurrencyDetailsSearchRequest(expression))
        when (response.resultCode) {

            SUCCESS -> {
                emit(
                    CryptoDetailsRequestResult.Content(
                        mapper.map((response as CryptocurrencyDetailsResponse))
                    )
                )
            }

            ERROR_404 -> {
                emit(CryptoDetailsRequestResult.Error)
            }

            INCORRECT_REQUEST -> {
                emit(CryptoDetailsRequestResult.Error)
            }

            CONNECTION_ERROR -> {
                emit(CryptoDetailsRequestResult.Error)
            }

            else -> {
                emit(CryptoDetailsRequestResult.Error)
            }


        }
    }
}