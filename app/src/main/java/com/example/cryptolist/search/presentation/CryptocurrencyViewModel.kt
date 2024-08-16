package com.example.cryptolist.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.search.domain.interactor.CryptocurrencyInteractor
import com.example.cryptolist.search.domain.model.Cryptocurrency
import kotlinx.coroutines.launch

class CryptocurrencyViewModel(
    private val interactor: CryptocurrencyInteractor
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<CryptocurrenciesSate>()
    val stateLiveData: LiveData<CryptocurrenciesSate> = _stateLiveData

    init {
        getCryptocurrencies("usd")
    }


    private fun getCryptocurrencies(symbolSearch: String) {
        renderSate(CryptocurrenciesSate.Loading)

        viewModelScope.launch {
            interactor
                .searchCryptocurrency(symbolSearch)
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    private fun processResult(foundCryptocurrencies: List<Cryptocurrency>?, errorMessage: String?) {
        val cryptocurrencies = mutableListOf<Cryptocurrency>()

        if (foundCryptocurrencies != null) {
            cryptocurrencies.addAll(foundCryptocurrencies)
        }

        when {
            errorMessage != null -> {
                renderSate(CryptocurrenciesSate.Error)
            }

            else -> {
                renderSate(CryptocurrenciesSate.Content(cryptocurrencies = cryptocurrencies))
            }
        }
    }

    private fun renderSate(state: CryptocurrenciesSate) {
        _stateLiveData.postValue(state)
    }


}