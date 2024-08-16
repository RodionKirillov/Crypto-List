package com.example.cryptolist.search.data.api

import com.example.cryptolist.search.data.dto.CryptocurrencyItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoingeckoApiService {

    //https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd

    @Headers("accept: application/json")
    @GET("/coins/markets")
    suspend fun getCryptocurrencyList(
        @Query("vs_currency") vsCurrency: String
    ): List<CryptocurrencyItem>
}