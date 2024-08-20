package com.example.cryptolist.util

import android.app.Application

object ResourceProvider {

    lateinit var application: Application

    fun initResourceProvider(application: Application) {
        ResourceProvider.application = application
    }

    fun getString(id: Int): String {
        return application.getString(id)
    }

}