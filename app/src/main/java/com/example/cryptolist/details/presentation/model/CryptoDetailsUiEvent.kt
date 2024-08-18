package com.example.cryptolist.details.presentation.model

sealed interface CryptoDetailsUiEvent {

    object ClickOnRefreshButton : CryptoDetailsUiEvent
}
