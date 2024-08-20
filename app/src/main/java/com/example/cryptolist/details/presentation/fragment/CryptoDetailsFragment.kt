package com.example.cryptolist.details.presentation.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cryptolist.databinding.FragmentCryptoDetailsBinding
import com.example.cryptolist.details.domain.model.CryptocurrencyDetails
import com.example.cryptolist.details.presentation.model.CryptoDetailsUiEvent
import com.example.cryptolist.details.presentation.model.DetailsState
import com.example.cryptolist.details.presentation.view_model.CryptoDetailsViewModel
import com.example.cryptolist.util.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CryptoDetailsFragment : BindingFragment<FragmentCryptoDetailsBinding>() {

    private val cryptoID by lazy {
        requireArguments().getString(CRYPTO_ID)
    }

    private val cryptoName by lazy {
        requireArguments().getString(CRYPTO_NAME)
    }

    private val viewModel: CryptoDetailsViewModel by viewModel {
        parametersOf(cryptoID)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCryptoDetailsBinding {
        return FragmentCryptoDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
    }

    private fun setupOnClickListeners() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
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
            toolBar.title = cryptoName.toString()
        }
    }

    private fun initDetails(details: CryptocurrencyDetails) {
        with(binding) {
            tvCryptoDescription.text = Html.fromHtml(
                details.description, Html.FROM_HTML_MODE_COMPACT
            )
            tvCryptoDescription.movementMethod = LinkMovementMethod.getInstance()
            toolBar.title = details.name
            Glide.with(requireContext())
                .load(details.image)
                .centerCrop()
                .into(ivCrypto)
            tvCryptoCategories.text = details.categories.joinToString()
        }
    }

    companion object {

        private const val CRYPTO_ID = "CRYPTO_ID"
        private const val CRYPTO_NAME = "CRYPTO_NAME"

        fun createArgs(cryptoID: String, cryptoName: String): Bundle =
            bundleOf(
                CRYPTO_ID to cryptoID,
                CRYPTO_NAME to cryptoName
            )
    }
}