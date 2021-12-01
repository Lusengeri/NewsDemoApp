package com.alsoftware.newsdemoapp

import android.app.Application
import android.content.Context

class GlobalApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}