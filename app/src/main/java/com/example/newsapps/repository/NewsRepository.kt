package com.example.newsapps.repository

import androidx.lifecycle.liveData
import com.example.newsapps.Constants.apiKey
import com.example.newsapps.db.NewsDataBase
import com.example.newsapps.models.Article
import com.example.newsapps.network.NewsApi
import com.example.newsapps.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsRepository(private val api: NewsApi, private val db: NewsDataBase) {

    private val everyThingDefaultSize = 10
    private val ru = "ru"

    fun getEverything(query: String?, page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.getEverything(query, apiKey, everyThingDefaultSize, page)
            emit(Resource.success(data = result))
            result.articles.let { db.newsDao().insertAllArticles(it) }
//            db.newsDao().insertAllArticle(result.articles)
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertFavoriteArticle(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            article.isFavorite = true
            db.newsDao().insertArticle(article)
        }
    }

    fun getAllArticles() = liveData(Dispatchers.IO){
        emit(db.newsDao().getAllArticles())
    }

    fun getFavoritesArticles() = liveData(Dispatchers.IO) {
        emit(db.newsDao().getFavoriteArticles())
    }

    fun getNewsTopHeadlines(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.getNewsTopHeadlines(ru, apiKey, page, everyThingDefaultSize)
            emit(Resource.success(data = result))
            result.articles.let { db.newsDao().insertAllArticles(it) }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error"))
        }
    }

    fun deleteAllArticles() {
        CoroutineScope(Dispatchers.IO).launch {
            db.newsDao().deleteAllArticles()
        }
    }

    fun deleteFavoriteArticle(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            db.newsDao().deleteFavoriteArticle(article)
            article.isFavorite = false
        }
    }
}

//    fun getEverything(query: String?, page: Int): MutableLiveData<MutableList<Article>>? {
//        val data: MutableLiveData<MutableList<Article>>? = MutableLiveData()
//        api.getEverything(query, apiKey, everyThingDefaultSize, page)
//            .enqueue(object : Callback<ResponseBody>{
//                override fun onResponse( call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    data?.value = response.body()?.articles
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable){
//                    Log.d("ololo", t.message.toString())
//                }
//            })
//        return data
//    }

//    fun getNewsTopHeadlines(page: Int): MutableLiveData<MutableList<Article>>?{
//        var news: MutableLiveData<MutableList<Article>>? = MutableLiveData()
//        api.getNewsTopHeadlines(ru, apiKey, page, everyThingDefaultSize)
//            .enqueue(object :Callback<ResponseBody>{
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    news?.value = response.body()?.articles
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    Log.d("ololo", t.message.toString())
//                }
//
//            })
//        return news
//    }
