package com.example.newsapps.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapps.models.Article
import com.example.newsapps.repository.NewsRepository

class NewsViewModel: ViewModel() {

    var articles = MutableLiveData<MutableList<Article>>()

    fun getEverything(query: String, page: Int){
        articles = NewsRepository().getEverything(query, page)!!
    }

    fun getNewsTopHeadlines(page: Int){
        articles = NewsRepository().getNewsTopHeadlines(page)!!
    }
}