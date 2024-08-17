package com.example.cryptolist.search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptolist.databinding.FragmentCryptoListsBinding
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.presentation.model.CryptocurrenciesSate
import com.example.cryptolist.search.presentation.view_model.CryptocurrencyViewModel
import com.example.cryptolist.search.presentation.adapter.CryptocurrencyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CryptoListsFragment : Fragment() {

    private var _binding: FragmentCryptoListsBinding? = null
    private val binding get() = _binding!!

    private val cryptocurrencyViewModel: CryptocurrencyViewModel by viewModel()
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
        cryptocurrencyViewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
    }

    private fun setupRecyclerView() {
        cryptocurrenciesAdapter = CryptocurrencyAdapter()
        binding.rvCryptocurrencies.adapter = cryptocurrenciesAdapter
        cryptocurrenciesAdapter.onCryptocurrencyClickListener = { }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CryptoListsFragment()
    }
}