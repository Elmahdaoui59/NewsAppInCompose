package com.example.newsappincompose.navigation.screens.common

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsappincompose.data.repository.NewsRepository
import com.example.newsappincompose.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    val getBreakingNews = repository.getNews()
    val getFavoriteNews = repository.getFavoriteNews()
    private val _searchedNews = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val searchedNews = _searchedNews

    fun searchForNews(query: String) {
        viewModelScope.launch {
            repository.searchForNews(query).cachedIn(viewModelScope).collect {
                _searchedNews.value = it
            }
        }
    }


    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateArticle(article: Article) {
        viewModelScope.launch {
            repository.updateArticle(article)
        }
    }

}