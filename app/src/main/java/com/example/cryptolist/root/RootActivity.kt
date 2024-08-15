package com.example.cryptolist.root

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptolist.R
import com.example.cryptolist.databinding.ActivityRootBinding
import com.example.cryptolist.search.presentation.CryptoListsFragment

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        supportFragmentManager.beginTransaction().add(
            R.id.fragmentContainer,
            CryptoListsFragment.newInstance()
        ).commit()
    }
}