package com.example.cryptolist.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptolist.databinding.FragmentCryptoRubListBinding
import com.example.cryptolist.databinding.FragmentCryptoUsdListBinding

class CryptoUsdListFragment : Fragment() {

    private var _binding: FragmentCryptoUsdListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoUsdListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        (parentFragment as? SelectPage)?.selectPage(page = 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = CryptoUsdListFragment()
    }
}