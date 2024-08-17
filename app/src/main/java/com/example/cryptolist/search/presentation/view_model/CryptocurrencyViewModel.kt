package com.example.cryptolist.search.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.search.domain.impl.GetCryptocurrencyListUseCase
import com.example.cryptolist.search.domain.model.RUB_CURRENCY
import com.example.cryptolist.search.domain.model.RequestResult
import com.example.cryptolist.search.domain.model.USD_CURRENCY
import com.example.cryptolist.search.presentation.model.CryptocurrenciesSate
import com.example.cryptolist.search.presentation.model.CryptocurrencyUiEvent
import kotlinx.coroutines.launch

class CryptocurrencyViewModel(
    private val getCryptocurrencyListUseCase: GetCryptocurrencyListUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<CryptocurrenciesSate>()
    val stateLiveData: LiveData<CryptocurrenciesSate> = _stateLiveData

    init {
        getCryptocurrencies(USD_CURRENCY)
    }

    fun onUiEvent(event: CryptocurrencyUiEvent) {
        when (event) {

            is CryptocurrencyUiEvent.UsdCurrencyClick -> {
                getCryptocurrencies(USD_CURRENCY)
            }

            is CryptocurrencyUiEvent.RubCurrencyClick -> {
                getCryptocurrencies(RUB_CURRENCY)
            }
        }
    }

    private fun getCryptocurrencies(symbolSearch: String) {
        renderSate(CryptocurrenciesSate.Loading)

        viewModelScope.launch {
            getCryptocurrencyListUseCase
                .invoke(symbolSearch)
                .collect { requestResult ->
                    processResult(requestResult)
                }
        }
    }

    private fun processResult(requestResult: RequestResult) {
        when (requestResult) {

            is RequestResult.RequestContent -> {
                renderSate(
                    CryptocurrenciesSate.Content(cryptocurrencies = requestResult.cryptocurrencies)
                )
            }

            is RequestResult.Error -> {
                renderSate(CryptocurrenciesSate.Error)
            }
        }
    }

    private fun renderSate(state: CryptocurrenciesSate) {
        _stateLiveData.postValue(state)
    }
}