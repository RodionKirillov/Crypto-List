package com.example.cryptolist.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptolist.databinding.FragmentCryptoListsBinding

class CryptoListsFragment : Fragment(), SelectPage {

    private var _binding: FragmentCryptoListsBinding? = null
    private val binding get() = _binding!!

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

        val adapter = PagerAdapter(hostFragment = this)
        binding.pager.adapter = adapter
        setupOnClickListeners()

    }

    private fun setupOnClickListeners() {
        binding.chipsUsd.setOnClickListener {
            viewPagerNavigate(FRAGMENT_USD)
        }
        binding.chipsRub.setOnClickListener {
            viewPagerNavigate(FRAGMENT_RUB)
        }
    }

    private fun viewPagerNavigate(page: Int) {
        binding.pager.currentItem = page
    }

    override fun selectPage(page: Int) {
        if (page == FRAGMENT_USD) {
            binding.chipsUsd.isChecked = true
            binding.chipsRub.isChecked = false
        } else {
            binding.chipsUsd.isChecked = false
            binding.chipsRub.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FRAGMENT_USD = 0
        private const val FRAGMENT_RUB = 1

        fun newInstance() = CryptoListsFragment()
    }
}