package com.example.newsappincompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappincompose.data.remote.NewsApi
import com.example.newsappincompose.model.Article
import com.example.newsappincompose.util.Constants.STARTING_PAGE_NUMBER

class SearchPagingSource(
    private val query: String,
    private val newsApi: NewsApi
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            var i = -100L
            val pageNumber = params.key ?: STARTING_PAGE_NUMBER
            val response = newsApi.searchForNews(query, pageNumber)
            val articles = response.articles.map {
                /*here i'm just trying to construct a unique keys for the articles because
                 they don't have ids by default, so the
                 the lazyColumn can reference articles properly {key= article.id}*/
                it.copy(id = (-pageNumber.toLong() + i--))
            }
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (articles.isEmpty()) null else pageNumber + 1
            LoadResult.Page(
                data = articles,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}