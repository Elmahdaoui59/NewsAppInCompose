package com.example.newsappincompose.navigation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappincompose.navigation.screens.common.ListContent
import com.example.newsappincompose.navigation.screens.common.NewsViewModel
import com.example.newsappincompose.navigation.screens.destinations.ArticleNavScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchScreen(
    searchViewModel: NewsViewModel,
    navigator: DestinationsNavigator,
    navController: NavController
) {
    val searchQuery by searchViewModel.searchQuery
    val articles = searchViewModel.searchedNews.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(it) },
                onSearchClicked = {searchViewModel.searchForNews(it)}
            ) {navController.popBackStack()}
        },

        ) {
        ListContent(
            items = articles,
            onArticleClicked = {navigator.navigate(ArticleNavScreenDestination(it))}
        )
    }

}