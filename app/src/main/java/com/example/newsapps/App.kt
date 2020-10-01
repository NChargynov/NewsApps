package com.example.newsapps

import android.app.Application
import com.example.newsapps.network.NewsApi
import com.example.newsapps.network.RetrofitClient

class App : Application(){

    val retrofitClient = RetrofitClient()

    fun provideNews() = retrofitClient.provideRetrofit.create(NewsApi::class.java)

    override fun onCreate() {
        super.onCreate()
        provideNews()
    }
}