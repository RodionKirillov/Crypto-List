package com.example.cryptolist

import android.app.Application
import com.example.cryptolist.di.dataModule
import com.example.cryptolist.di.domainModule
import com.example.cryptolist.di.presentationModule
import com.example.cryptolist.util.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ResourceProvider.initResourceProvider(this)

        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}