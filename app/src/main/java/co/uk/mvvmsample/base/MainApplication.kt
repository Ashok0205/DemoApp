package co.uk.mvvmsample.base

import android.app.Application
import android.content.Context

class MainApplication : Application(){

    companion object {
        private lateinit var instance: MainApplication
        fun get(): MainApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getContext(): Context {
        return applicationContext
    }
}