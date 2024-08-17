package com.example.cryptolist.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptolist.databinding.CryptocurrencyItemBinding
import com.example.cryptolist.search.domain.model.Cryptocurrency
import com.example.cryptolist.search.presentation.view_holder.CryptocurrencyViewHolder

class CryptocurrencyAdapter :
    ListAdapter<Cryptocurrency, CryptocurrencyViewHolder>(CryptocurrencyItemDiffCallback()) {

    var onCryptocurrencyClickListener: ((Cryptocurrency) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        val binding = CryptocurrencyItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptocurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onCryptocurrencyClickListener?.invoke(item)
        }
    }
}