package com.example.newsapps.di

import com.example.newsapps.network.RetrofitClient
import com.example.newsapps.repository.NewsRepository
import com.example.newsapps.ui.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var newsModule = module {
    single { RetrofitClient().provideNews() }
    viewModel { NewsViewModel(get()) }
    factory { NewsRepository(get()) }
}