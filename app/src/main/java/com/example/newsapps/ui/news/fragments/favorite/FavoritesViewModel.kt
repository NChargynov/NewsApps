package com.example.newsapps.ui.news.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapps.base.BaseViewModel
import com.example.newsapps.models.Article
import com.example.newsapps.repository.NewsRepository

class FavoritesViewModel(private val newsRepository: NewsRepository) : BaseViewModel() {

    init {
        getFavoritesArticles()
    }

    fun getFavoritesArticles(): LiveData<MutableList<Article>>{
        return newsRepository.getFavoritesArticles()
    }
}