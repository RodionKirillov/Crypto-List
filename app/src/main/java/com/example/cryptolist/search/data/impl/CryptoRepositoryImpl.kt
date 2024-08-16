package com.example.cryptolist.search.data.impl

import com.example.cryptolist.search.data.api.NetworkClient
import com.example.cryptolist.search.data.dto.CryptocurrencyListResponse
import com.example.cryptolist.search.data.dto.CryptocurrencySearchRequest
import com.example.cryptolist.search.data.mapper.SearchDtoMapper
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.domain.repository.CryptoRepository
import com.example.cryptolist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: SearchDtoMapper
) : CryptoRepository {

    override fun searchCryptocurrency(
        expression: String
    ): Flow<Resource<List<Cryptocurrency>>> = flow {
        val response = networkClient.doRequest(CryptocurrencySearchRequest(expression))
        when (response.resultCode) {

            200 -> {
                emit(
                    Resource.Success(
                        mapper.mapCryptocurrencyFromCryptocurrencyDto(
                            (response as CryptocurrencyListResponse).cryptocurrencyList
                        )
                    )
                )
            }

            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
        }
    }
}