package com.example.cryptolist.di

import com.example.cryptolist.search.data.api.CoingeckoApiService
import com.example.cryptolist.search.data.api.NetworkClient
import com.example.cryptolist.search.data.api.RetrofitNetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.coingecko.com/api/v3/"

val dataModule = module {



    single<NetworkClient> {
        RetrofitNetworkClient(
            coingeckoApiService = get()
        )
    }

    single<CoingeckoApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoingeckoApiService::class.java)
    }
}