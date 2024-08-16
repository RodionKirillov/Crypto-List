package com.example.cryptolist.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptolist.databinding.FragmentCryptoListsBinding

class CryptoListsFragment : Fragment() {

    private var _binding: FragmentCryptoListsBinding? = null
    private val binding get() = _binding!!

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

    }

    private fun setupRecyclerView() {
        cryptocurrenciesAdapter = CryptocurrencyAdapter()
        binding.rvCryptocurrencies.adapter = cryptocurrenciesAdapter
        cryptocurrenciesAdapter.onCryptocurrencyClickListener = { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CryptoListsFragment()
    }
}