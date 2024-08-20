package com.example.cryptolist.search.data.api

import com.example.cryptolist.search.data.dto.CryptocurrencyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoingeckoApiService {

    //https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd

    @GET("coins/markets")
    suspend fun getCryptocurrencyList(
        @Query("vs_currency") vsCurrency: String
    ): List<CryptocurrencyDto>

    @GET("coins/{id}")
    suspend fun getCryptocurrencyDetail(
        @Path("id") id: String
    ): com.example.cryptolist.details.data.dto.CryptocurrencyDetailsResponse
}