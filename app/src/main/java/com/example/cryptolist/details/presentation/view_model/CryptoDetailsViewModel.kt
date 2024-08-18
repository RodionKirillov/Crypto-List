package com.example.cryptolist.details.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.details.domain.model.CryptoDetailsRequestResult
import com.example.cryptolist.details.domain.use_cases.GetCryptocurrencyDetailsUseCase
import com.example.cryptolist.details.presentation.model.CryptoDetailsUiEvent
import com.example.cryptolist.details.presentation.model.DetailsState
import kotlinx.coroutines.launch

class CryptoDetailsViewModel(
    private val cryptoId: String,
    private val getCryptocurrencyDetailsUseCase: GetCryptocurrencyDetailsUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<DetailsState>()
    val stateLiveData: LiveData<DetailsState> = _stateLiveData

    init {
        getDetails(cryptoId)
    }

    fun onUiEvent(event: CryptoDetailsUiEvent) {
        when (event) {
            is CryptoDetailsUiEvent.ClickOnRefreshButton -> {
                getDetails(cryptoId)
            }
        }
    }

    private fun getDetails(cryptoId: String) {
        renderSate(DetailsState.Loading)

        viewModelScope.launch {
            getCryptocurrencyDetailsUseCase
                .invoke(cryptoId)
                .collect { details ->
                    processResult(details)
                }
        }
    }

    private fun processResult(cryptoDetails: CryptoDetailsRequestResult) {
        when (cryptoDetails) {

            is CryptoDetailsRequestResult.Content -> {
                renderSate(
                    DetailsState.Content(details = cryptoDetails.details)
                )
            }

            is CryptoDetailsRequestResult.Error -> {
                renderSate(DetailsState.Error)
            }
        }
    }

    private fun renderSate(state: DetailsState) {
        _stateLiveData.postValue(state)
    }
}