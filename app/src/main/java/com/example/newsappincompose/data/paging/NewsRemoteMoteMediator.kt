package com.example.newsappincompose.data.paging

import androidx.paging.*
import androidx.room.withTransaction
import com.example.newsappincompose.data.local.NewsDatabase
import com.example.newsappincompose.data.remote.NewsApi
import com.example.newsappincompose.model.Article
import com.example.newsappincompose.model.NewsRemoteKeys
import com.example.newsappincompose.util.Constants.STARTING_PAGE_NUMBER
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMoteMediator(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, Article>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: STARTING_PAGE_NUMBER
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {

            val apiResponse = newsApi.getArticles( pageNumber = page,
                pageSize = state.config.pageSize
            )
            val articles = apiResponse.articles
            val endOfPaginationReached = articles.isEmpty()
            newsDatabase.withTransaction {
                //clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    newsDatabase.newsRemoteKeysDao().deleteRemoteKeys()
                    newsDatabase.newsDao().deleteNews()
                }
                val prevKey = if (page == STARTING_PAGE_NUMBER) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = articles.map {
                    NewsRemoteKeys(id = it.id, prevKey, nextKey)
                }
                newsDatabase.newsRemoteKeysDao().insertKeys(keys)
                newsDatabase.newsDao().insertNews(articles)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }

    }

        private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): NewsRemoteKeys? {
            return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { article ->
                    newsDatabase.newsRemoteKeysDao().getNewsRemoteKey(article.id)
                }
        }

        private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): NewsRemoteKeys? {
            return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { article ->
                    newsDatabase.newsRemoteKeysDao().getNewsRemoteKey(article.id)
                }
        }

        private suspend fun getRemoteKeyClosestToCurrentPosition(
            state: PagingState<Int, Article>
        ): NewsRemoteKeys? {
            return state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { articleId ->
                    newsDatabase.newsRemoteKeysDao().getNewsRemoteKey(articleId)
                }
            }
        }

}