package com.example.newsapps.network

import com.example.newsapps.models.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun getEverything(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): Call<ResponseBody>

    @GET("/v2/top-headlines")
    fun getNewsTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): Call<ResponseBody>
}