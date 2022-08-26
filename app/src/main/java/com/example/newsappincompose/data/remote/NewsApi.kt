package com.example.newsappincompose.data.remote

import com.example.newsappincompose.BuildConfig.API_KEY
import com.example.newsappincompose.model.NewsResponse
import com.example.newsappincompose.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") country: String = "us",
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int = Constants.pageSize,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchForNews(
        @Query("q") searchQuery:String,
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int = Constants.pageSize,
        @Query("apiKey") apiKey: String = API_KEY
    ):NewsResponse
}