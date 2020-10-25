package com.example.newsapps.di

import com.example.newsapps.db.DataBaseClient
import com.example.newsapps.network.RetrofitClient
import com.example.newsapps.repository.NewsRepository
import com.example.newsapps.ui.details.DetailsViewModel
import com.example.newsapps.ui.news.NewsViewModel
import com.example.newsapps.ui.news.fragments.favorite.FavoritesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var newsModule = module {
    single { RetrofitClient().provideNews() }
    single { DataBaseClient().provideDatabase(androidContext()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    factory { NewsRepository(get(), get()) }
}