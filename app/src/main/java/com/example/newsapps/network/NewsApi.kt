package com.example.newsapps.network

import com.example.newsapps.models.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): ResponseBody

    @GET("/v2/top-headlines")
     suspend fun getNewsTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): ResponseBody
}