package com.example.cryptolist.di

import com.example.cryptolist.details.presentation.view_model.CryptoDetailsViewModel
import com.example.cryptolist.search.presentation.view_model.CryptocurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { (cryptoId: String) ->
        CryptoDetailsViewModel(
            cryptoId = cryptoId,
            getCryptocurrencyDetailsUseCase = get()
        )
    }

    viewModel {
        CryptocurrencyViewModel(
            getCryptocurrencyListUseCase = get()
        )
    }
}