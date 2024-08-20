package com.example.cryptolist.di

import com.example.cryptolist.details.data.impl.CryptoDetailsRepositoryImpl
import com.example.cryptolist.details.domain.repository.CryptoDetailsRepository
import com.example.cryptolist.details.domain.use_cases.GetCryptocurrencyDetailsUseCase
import com.example.cryptolist.search.data.impl.CryptoListRepositoryImpl
import com.example.cryptolist.search.domain.use_cases.GetCryptocurrencyListUseCase
import com.example.cryptolist.search.domain.repository.CryptoListRepository
import org.koin.dsl.module

val domainModule = module {

    single {
        GetCryptocurrencyDetailsUseCase(
            repository = get()
        )
    }

    single {
        GetCryptocurrencyListUseCase(
            repository = get()
        )
    }

    single<CryptoDetailsRepository> {
        CryptoDetailsRepositoryImpl(
            networkClient = get(),
            mapper = get()
        )
    }

    single<CryptoListRepository> {
        CryptoListRepositoryImpl(
            networkClient = get(),
            mapper = get()
        )
    }
}