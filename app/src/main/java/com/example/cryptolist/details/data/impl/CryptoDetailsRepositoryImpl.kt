package com.example.cryptolist.details.data.impl

import com.example.cryptolist.details.domain.repository.CryptoDetailsRepository
import com.example.cryptolist.search.data.api.CONNECTION_ERROR
import com.example.cryptolist.search.data.api.ERROR_404
import com.example.cryptolist.search.data.api.INCORRECT_REQUEST
import com.example.cryptolist.search.data.api.SUCCESS
import com.example.cryptolist.details.data.dto.CryptocurrencyDetailsSearchRequest
import com.example.cryptolist.search.data.mapper.SearchDtoMapper
import com.example.cryptolist.search.data.source.NetworkClient
import com.example.cryptolist.details.domain.model.CryptoDetailsRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoDetailsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: SearchDtoMapper
) : CryptoDetailsRepository {

    override fun getCryptocurrencyDetails(
        expression: String
    ): Flow<CryptoDetailsRequestResult> = flow {
        val response = networkClient.doRequest(CryptocurrencyDetailsSearchRequest(expression))
        when (response.resultCode) {

            SUCCESS -> {
                emit(
                    CryptoDetailsRequestResult.Content(
                        mapper.map((response as com.example.cryptolist.details.data.dto.CryptocurrencyDetailsResponse))
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