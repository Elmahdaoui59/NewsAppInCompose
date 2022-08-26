package com.example.newsappincompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsappincompose.data.local.NewsDatabase
import com.example.newsappincompose.data.paging.NewsRemoteMoteMediator
import com.example.newsappincompose.data.paging.SearchPagingSource
import com.example.newsappincompose.data.remote.NewsApi
import com.example.newsappincompose.model.Article
import com.example.newsappincompose.util.Constants.pageSize
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getNews(): Flow<PagingData<Article>> {
        val pagingSourceFactory = {newsDatabase.newsDao().getNews()}
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            remoteMediator = NewsRemoteMoteMediator(newsApi, newsDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun updateArticle(article: Article) {
        newsDatabase.newsDao().updateArticle(article)
    }

    fun getFavoriteNews(): Flow<PagingData<Article>> {
        val pagingSourceFactory = {newsDatabase.newsDao().getFavoriteNews()}
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchForNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize= pageSize, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(query, newsApi) }
        ).flow
    }
}