package com.example.cryptolist.di

import com.example.cryptolist.search.data.impl.CryptoRepositoryImpl
import com.example.cryptolist.search.domain.use_cases.GetCryptocurrencyListUseCase
import com.example.cryptolist.search.domain.repository.CryptoRepository
import org.koin.dsl.module

val domainModule = module {

    single {
        GetCryptocurrencyListUseCase(
            repository = get()
        )
    }

    single<CryptoRepository> {
        CryptoRepositoryImpl(
            networkClient = get(),
            mapper = get()
        )
    }
}