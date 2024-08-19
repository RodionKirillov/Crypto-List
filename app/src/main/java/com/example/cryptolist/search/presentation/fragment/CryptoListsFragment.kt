package com.example.cryptolist.search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.cryptolist.R
import com.example.cryptolist.databinding.FragmentCryptoListsBinding
import com.example.cryptolist.details.presentation.fragment.CryptoDetailsFragment
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.presentation.adapter.CryptocurrencyAdapter
import com.example.cryptolist.search.presentation.model.CryptocurrenciesSate
import com.example.cryptolist.search.presentation.model.CryptocurrencyUiEvent
import com.example.cryptolist.search.presentation.view_model.CryptocurrencyViewModel
import com.example.cryptolist.util.BindingFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListsFragment : BindingFragment<FragmentCryptoListsBinding>() {

    private val viewModel: CryptocurrencyViewModel by viewModel()
    private lateinit var cryptocurrenciesAdapter: CryptocurrencyAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCryptoListsBinding {
        return FragmentCryptoListsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupOnClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
        viewModel.showSnackBar.observe(viewLifecycleOwner) {
            showSnackBar()
        }
    }

    private fun setupOnClickListeners() {
        binding.refreshButton.setOnClickListener {
            currentSymbolChecked()
        }
        binding.chipsUsd.setOnClickListener {
            clickOnUsdSymbol()
        }
        binding.chipsRub.setOnClickListener {
            clickOnRubSymbol()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            swipedOnRefresh()
        }
        cryptocurrenciesAdapter.onCryptoItemClickListener = { cryptoId ->
            launchCryptoDetailsFragment(cryptoId)
        }
    }

    private fun setupRecyclerView() {
        cryptocurrenciesAdapter = CryptocurrencyAdapter()
        binding.rvCryptocurrencies.adapter = cryptocurrenciesAdapter
    }

    private fun currentSymbolChecked() {
        if (binding.chipsUsd.isChecked) {
            viewModel.onUiEvent(CryptocurrencyUiEvent.UsdCurrencyClick)
        } else {
            viewModel.onUiEvent(CryptocurrencyUiEvent.RubCurrencyClick)
        }
    }

    private fun clickOnUsdSymbol() {
        viewModel.onUiEvent(CryptocurrencyUiEvent.UsdCurrencyClick)
        binding.chipsRub.isChecked = false
        binding.chipsUsd.isChecked = true
    }

    private fun clickOnRubSymbol() {
        viewModel.onUiEvent(CryptocurrencyUiEvent.RubCurrencyClick)
        binding.chipsUsd.isChecked = false
        binding.chipsRub.isChecked = true
    }

    private fun swipedOnRefresh() {
        viewModel.onUiEvent(CryptocurrencyUiEvent.SwipedOnRefresh)
    }

    private fun renderState(state: CryptocurrenciesSate) {
        when (state) {
            is CryptocurrenciesSate.Content -> showContent(state.cryptocurrencies)
            is CryptocurrenciesSate.Loading -> showLoading()
            is CryptocurrenciesSate.Error -> showError()
        }
    }

    private fun showSnackBar() {
        Snackbar.make(binding.root, getString(R.string.error_loading), Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red))
            .show()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showContent(cryptocurrencies: List<Cryptocurrency>) {
        with(binding) {
            llContent.visibility = View.VISIBLE
            llError.visibility = View.GONE
            llLoading.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        }
        cryptocurrenciesAdapter.submitList(cryptocurrencies)
    }

    private fun showLoading() {
        with(binding) {
            llLoading.visibility = View.VISIBLE
            llError.visibility = View.GONE
            llContent.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            llError.visibility = View.VISIBLE
            llContent.visibility = View.GONE
            llLoading.visibility = View.GONE
        }
    }

    private fun launchCryptoDetailsFragment(id: String) {
        if (clickDebounce()) {
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, CryptoDetailsFragment.newInstance(cryptoID = id))
                addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
    }

    companion object {

        fun newInstance() = CryptoListsFragment()
    }
}