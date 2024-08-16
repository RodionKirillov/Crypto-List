package com.example.cryptolist.di

import com.example.cryptolist.search.presentation.CryptocurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        CryptocurrencyViewModel(
            interactor = get()
        )
    }
}