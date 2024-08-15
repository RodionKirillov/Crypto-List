package com.example.cryptolist.search.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(hostFragment: Fragment) : FragmentStateAdapter(hostFragment) {

    override fun getItemCount(): Int = NUMBERS_OF_ELEMENTS

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            CryptoUsdListFragment.newInstance()
        } else {
            CryptoRubListFragment.newInstance()
        }
    }

    companion object {

        const val NUMBERS_OF_ELEMENTS = 2
    }
}