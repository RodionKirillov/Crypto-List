package com.example.cryptolist.search.presentation.view_holder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptolist.R
import com.example.cryptolist.databinding.CryptocurrencyItemBinding
import com.example.cryptolist.search.domain.model.Cryptocurrency
import java.text.NumberFormat
import java.util.Locale

class CryptocurrencyViewHolder(
    private val binding: CryptocurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Cryptocurrency) {

        with(binding) {
            tvCryptoItemName.text = model.name
            tvCryptoItemSymbol.text = model.symbol
            tvCryptoItemPrice.text = model.currentPrice
            tvCryptoItemPercent.text = addPlus(model.priceChangePercentage24h)
            cryptoItemPercentColor(model.priceChangePercentage24h)

            Glide.with(itemView.context)
                .load(model.image)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(ivCryptoItem)
        }
    }

    private fun addPlus(percent: Double): String {
        return if (percent > 0) {
            "+$percent"
        } else {
            percent.toString()
        }
    }

    private fun cryptoItemPercentColor(percent: Double) {
        if (percent < 0) {
            binding.tvCryptoItemPercent.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.red_percent)
            )
        } else {
            binding.tvCryptoItemPercent.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.green_percent)
            )
        }
    }
}