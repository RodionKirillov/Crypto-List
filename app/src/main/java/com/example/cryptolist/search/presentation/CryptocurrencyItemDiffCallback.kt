package com.example.cryptolist.search.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptolist.search.domain.model.Cryptocurrency

class CryptocurrencyItemDiffCallback : DiffUtil.ItemCallback<Cryptocurrency>() {

    override fun areItemsTheSame(oldItem: Cryptocurrency, newItem: Cryptocurrency): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cryptocurrency, newItem: Cryptocurrency): Boolean {
        return oldItem == newItem
    }
}