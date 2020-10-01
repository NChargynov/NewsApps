package com.example.newsapps.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapps.App
import com.example.newsapps.Constants.apiKey
import com.example.newsapps.models.Article
import com.example.newsapps.models.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository() {

    private val everyThingDefaultSize = 10
    private val ru = "ru"
    fun getEverything(query: String?, page: Int): MutableLiveData<MutableList<Article>>? {
        var data: MutableLiveData<MutableList<Article>>? = MutableLiveData()
        App().provideNews().getEverything(query, apiKey, everyThingDefaultSize, page)
            .enqueue(object : Callback<ResponseBody>{
                override fun onResponse( call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    data?.value = response.body()?.articles
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable){
                    Log.d("ololo", t.message.toString())
                }
            })
        return data
    }

    fun getNewsTopHeadlines(page: Int): MutableLiveData<MutableList<Article>>?{
        var news: MutableLiveData<MutableList<Article>>? = MutableLiveData()
        App().provideNews().getNewsTopHeadlines(ru, apiKey, page, everyThingDefaultSize)
            .enqueue(object :Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    news?.value = response.body()?.articles
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("ololo", t.message.toString())
                }

            })
        return news
    }
}