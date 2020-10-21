package com.example.newsapps

import android.app.Application
import com.example.newsapps.di.newsModule
import com.example.newsapps.network.NewsApi
import com.example.newsapps.network.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(newsModule)
        }
    }
}