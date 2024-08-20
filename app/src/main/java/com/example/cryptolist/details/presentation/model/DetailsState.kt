package com.example.cryptolist.details.presentation.model

import com.example.cryptolist.details.domain.model.CryptocurrencyDetails

sealed interface DetailsState {

    data class Content(
        val details: CryptocurrencyDetails
    ) : DetailsState

    object Error : DetailsState

    object Loading : DetailsState
}