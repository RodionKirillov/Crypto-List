package com.example.cryptolist.search.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolist.search.domain.model.CryptoListRequestResult
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.domain.model.Currency
import com.example.cryptolist.search.domain.use_cases.GetCryptocurrencyListUseCase
import com.example.cryptolist.search.presentation.model.CryptocurrenciesSate
import com.example.cryptolist.search.presentation.model.CryptocurrencyUiEvent
import com.example.cryptolist.util.SingleLiveEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CryptocurrencyViewModel(
    private val getCryptocurrencyListUseCase: GetCryptocurrencyListUseCase
) : ViewModel() {

    private var currentCurrency: Currency = Currency.UsdCurrency()

    private val _stateLiveData = MutableLiveData<CryptocurrenciesSate>()
    val stateLiveData: LiveData<CryptocurrenciesSate> = _stateLiveData

    private val _showSnackBar = SingleLiveEvent<Unit>()
    val showSnackBar: LiveData<Unit> = _showSnackBar

    init {
        getCryptocurrencies(currentCurrency.value)
    }

    fun onUiEvent(event: CryptocurrencyUiEvent) {
        when (event) {

            is CryptocurrencyUiEvent.UsdCurrencyClick -> {
                currentCurrency = Currency.UsdCurrency()
                getCryptocurrencies(currentCurrency.value)
            }

            is CryptocurrencyUiEvent.RubCurrencyClick -> {
                currentCurrency = Currency.RubCurrency()
                getCryptocurrencies(currentCurrency.value)
            }

            is CryptocurrencyUiEvent.SwipedOnRefresh -> {
                updateList()
            }
        }
    }

    private fun getCryptocurrencies(symbolSearch: String) {
        renderSate(CryptocurrenciesSate.Loading)
        getCryptocurrencyListUseCase
            .invoke(symbolSearch)
            .onEach { requestResult ->
                processResult(requestResult)
            }
            .launchIn(viewModelScope)
    }

    private fun updateList() {
        getCryptocurrencyListUseCase
            .invoke(currentCurrency.value)
            .onEach { requestResult ->
                updateResult(requestResult)
            }
            .launchIn(viewModelScope)
    }

    private fun processResult(cryptoListRequestResult: CryptoListRequestResult) {
        when (cryptoListRequestResult) {

            is CryptoListRequestResult.Content -> {
                showContent(cryptoListRequestResult.cryptocurrencies)
            }

            is CryptoListRequestResult.Error -> {
                renderSate(CryptocurrenciesSate.Error)
            }
        }
    }

    private fun updateResult(cryptoListRequestResult: CryptoListRequestResult) {
        when (cryptoListRequestResult) {

            is CryptoListRequestResult.Content -> {
                showContent(cryptoListRequestResult.cryptocurrencies)
            }

            is CryptoListRequestResult.Error -> {
                _showSnackBar.postValue(Unit)
            }
        }
    }

    private fun showContent(cryptoList: List<Cryptocurrency>) {
        renderSate(
            CryptocurrenciesSate.Content(
                cryptocurrencies = cryptoList
            )
        )
    }

    private fun renderSate(state: CryptocurrenciesSate) {
        _stateLiveData.postValue(state)
    }
}