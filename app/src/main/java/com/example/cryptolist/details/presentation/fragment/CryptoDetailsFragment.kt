package com.example.cryptolist.details.presentation.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cryptolist.databinding.FragmentCryptoDetailsBinding
import com.example.cryptolist.details.domain.model.CryptocurrencyDetails
import com.example.cryptolist.details.presentation.model.CryptoDetailsUiEvent
import com.example.cryptolist.details.presentation.model.DetailsState
import com.example.cryptolist.details.presentation.view_model.CryptoDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CryptoDetailsFragment : Fragment() {

    private var _binding: FragmentCryptoDetailsBinding? = null
    private val binding get() = _binding!!

    private val cryptoID by lazy {
        requireArguments().getString(CRYPTO_ID)
    }

    private val viewModel: CryptoDetailsViewModel by viewModel {
        parametersOf(cryptoID)
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
        setupOnClickListeners()
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
    }

    private fun setupOnClickListeners() {
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.refreshButton.setOnClickListener {
            viewModel.onUiEvent(CryptoDetailsUiEvent.ClickOnRefreshButton)
        }
    }

    private fun renderState(state: DetailsState) {
        when (state) {
            is DetailsState.Content -> showContent(state.details)
            is DetailsState.Error -> showError()
            is DetailsState.Loading -> showLoading()
        }
    }

    private fun showContent(details: CryptocurrencyDetails) {
        with(binding) {
            llError.visibility = View.GONE
            llLoading.visibility = View.GONE
            svDetails.visibility = View.VISIBLE
        }
        initDetails(details)
    }

    private fun showLoading() {
        with(binding) {
            llError.visibility = View.GONE
            llLoading.visibility = View.VISIBLE
            svDetails.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            llError.visibility = View.VISIBLE
            llLoading.visibility = View.GONE
            svDetails.visibility = View.GONE
        }
    }

    private fun initDetails(details: CryptocurrencyDetails) {
        binding.tvCryptoDescription.text = Html.fromHtml(
            details.description, Html.FROM_HTML_MODE_COMPACT
        )
        binding.tvCryptoDescription.movementMethod = LinkMovementMethod.getInstance()
        binding.toolBar.title = details.name
        Glide.with(requireContext())
            .load(details.image)
            .centerCrop()
            .into(binding.ivCrypto)
        binding.tvCryptoCategories.text = details.categories.joinToString()
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