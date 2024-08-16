package com.example.cryptolist.search.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptolist.R
import com.example.cryptolist.databinding.CryptocurrencyItemBinding
import com.example.cryptolist.search.domain.model.Cryptocurrency

class CryptocurrencyViewHolder(
    private val binding: CryptocurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Cryptocurrency) {

        with(binding) {
            tvCryptoItemName.text = model.name
            tvCryptoItemSymbol.text = model.symbol
            tvCryptoItemPrice.text = model.currentPrice.toString()
            tvCryptoItemPercent.text = model.priceChangePercentage24h.toString()

            Glide.with(itemView.context)
                .load(model.image)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(ivCryptoItem)
        }
    }
}