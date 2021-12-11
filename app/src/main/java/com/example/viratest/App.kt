package com.example.viratest

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this
    }

    fun getContext(): Context? {
        return context
    }

    companion object {
        @get:Synchronized
        var instance: App? = null
        private var context: Context? = null
    }
}