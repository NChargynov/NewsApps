package com.example.newsapps.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapps.base.BaseViewModel
import com.example.newsapps.models.Article
import com.example.newsapps.models.ResponseBody
import com.example.newsapps.network.Resource
import com.example.newsapps.repository.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository) : BaseViewModel() {

    var articles = MutableLiveData<MutableList<Article>>()
    var page = 1
    var isLastPage = false
    var isLoading = false

    init {
        getEverything("bitcoin")
        getNewsTopHeadlines()
    }

    fun getEverything(query: String): LiveData<Resource<ResponseBody>> {
        page += 1
        return newsRepository.getEverything(query, page)
    }

    fun getNewsTopHeadlines(): LiveData<Resource<ResponseBody>> {
        page += 1
        return newsRepository.getNewsTopHeadlines(page)
    }

    fun insertFavoriteNew(article: Article){
        newsRepository.insertFavoriteArticle(article)
    }
}