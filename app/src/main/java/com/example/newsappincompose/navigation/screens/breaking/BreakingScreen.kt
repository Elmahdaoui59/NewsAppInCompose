package com.example.newsappincompose.navigation.screens.breaking

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappincompose.navigation.screens.common.ListContent
import com.example.newsappincompose.navigation.screens.common.NewsViewModel
import com.example.newsappincompose.navigation.screens.destinations.ArticleNavScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun BreakingScreen(
    breakingNewsViewModel: NewsViewModel,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = { BreakingTopBar() }
    ) {
        ListContent(
            items = breakingNewsViewModel.getBreakingNews.collectAsLazyPagingItems()
        ) {
            navigator.navigate(ArticleNavScreenDestination(it))
        }
    }
}