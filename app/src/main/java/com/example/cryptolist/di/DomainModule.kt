package com.example.cryptolist.di

import com.example.cryptolist.search.data.impl.CryptoRepositoryImpl
import com.example.cryptolist.search.domain.impl.CryptocurrencyInteractorImpl
import com.example.cryptolist.search.domain.interactor.CryptocurrencyInteractor
import com.example.cryptolist.search.domain.repository.CryptoRepository
import org.koin.dsl.module

val domainModule = module {


    single<CryptocurrencyInteractor> {
        CryptocurrencyInteractorImpl(
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