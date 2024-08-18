package com.example.cryptolist.search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.cryptolist.R
import com.example.cryptolist.databinding.FragmentCryptoListsBinding
import com.example.cryptolist.details.presentation.fragment.CryptoDetailsFragment
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.presentation.adapter.CryptocurrencyAdapter
import com.example.cryptolist.search.presentation.model.CryptocurrenciesSate
import com.example.cryptolist.search.presentation.model.CryptocurrencyUiEvent
import com.example.cryptolist.search.presentation.view_model.CryptocurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListsFragment : Fragment() {

    private var _binding: FragmentCryptoListsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CryptocurrencyViewModel by viewModel()
    private lateinit var cryptocurrenciesAdapter: CryptocurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoListsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupOnClickListeners()
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderState(state)
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
    }

    private fun setupRecyclerView() {
        cryptocurrenciesAdapter = CryptocurrencyAdapter()
        binding.rvCryptocurrencies.adapter = cryptocurrenciesAdapter

        cryptocurrenciesAdapter.onCryptoItemClickListener = { cryptoId ->
            launchCryptoDetailsFragment(cryptoId)
        }
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

    private fun renderState(state: CryptocurrenciesSate) {
        when (state) {
            is CryptocurrenciesSate.Content -> showContent(state.cryptocurrencies)
            is CryptocurrenciesSate.Loading -> showLoading()
            is CryptocurrenciesSate.Error -> showError()
        }
    }

    private fun showContent(cryptocurrencies: List<Cryptocurrency>) {
        binding.llContent.visibility = View.VISIBLE
        binding.llError.visibility = View.GONE
        binding.llLoading.visibility = View.GONE
        cryptocurrenciesAdapter.submitList(cryptocurrencies)
    }

    private fun showLoading() {
        binding.llLoading.visibility = View.VISIBLE
        binding.llError.visibility = View.GONE
        binding.llContent.visibility = View.GONE
    }

    private fun showError() {
        binding.llError.visibility = View.VISIBLE
        binding.llContent.visibility = View.GONE
        binding.llLoading.visibility = View.GONE
    }

    private fun launchCryptoDetailsFragment(id: String) {
        parentFragmentManager.commit {
            replace(R.id.fragmentContainer, CryptoDetailsFragment.newInstance(cryptoID = id))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CryptoListsFragment()
    }
}