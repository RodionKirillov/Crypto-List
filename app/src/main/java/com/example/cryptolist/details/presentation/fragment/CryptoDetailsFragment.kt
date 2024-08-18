package com.example.cryptolist.details.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cryptolist.databinding.FragmentCryptoDetailsBinding
import com.example.cryptolist.databinding.FragmentCryptoListsBinding

class CryptoDetailsFragment : Fragment() {

    private var _binding: FragmentCryptoDetailsBinding? = null
    private val binding get() = _binding!!

    val cryptoID by lazy {
        requireArguments().getString(CRYPTO_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val CRYPTO_ID = "CRYPTO_ID"

        fun newInstance(cryptoID: String) = CryptoDetailsFragment().apply {
            arguments = bundleOf(CRYPTO_ID to cryptoID)
        }
    }
}