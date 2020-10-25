package com.example.newsapps.ui.details

import com.example.newsapps.base.BaseViewModel
import com.example.newsapps.models.Article
import com.example.newsapps.repository.NewsRepository

class DetailsViewModel(private val newsRepository: NewsRepository): BaseViewModel(){

    fun insertFavoriteNew(article: Article){
        newsRepository.insertFavoriteArticle(article)
    }

    fun deleteFavoriteArticle(article: Article){
        newsRepository.deleteFavoriteArticle(article)
    }
}