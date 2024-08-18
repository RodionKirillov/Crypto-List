package com.example.cryptolist.search.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.cryptolist.search.data.dto.CryptocurrencyDetailsSearchRequest
import com.example.cryptolist.search.data.dto.CryptocurrencyListResponse
import com.example.cryptolist.search.data.dto.CryptocurrencySearchRequest
import com.example.cryptolist.search.data.dto.Response
import com.example.cryptolist.search.data.dto.details_dto.CryptocurrencyDetailsResponse
import com.example.cryptolist.search.data.source.NetworkClient
import com.example.cryptolist.util.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val coingeckoApiService: CoingeckoApiService
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = CONNECTION_ERROR }
        }

        if ((dto !is CryptocurrencySearchRequest) && (dto !is CryptocurrencyDetailsSearchRequest)) {
            return Response().apply { resultCode = INCORRECT_REQUEST }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = when (dto) {
                    is CryptocurrencySearchRequest -> {
                        CryptocurrencyListResponse(
                            coingeckoApiService.getCryptocurrencyList(dto.expression)
                        )

                    }

                    else -> {
                        coingeckoApiService.getCryptocurrencyDetail(
                            (dto as CryptocurrencyDetailsSearchRequest).expression
                        )
                    }
                }

                response.apply { resultCode = SUCCESS }
            } catch (e: Throwable) {
                Response().apply { resultCode = ERROR_404 }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = ResourceProvider.application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}