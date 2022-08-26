package com.example.newsappincompose.navigation.screens.favorite

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappincompose.navigation.screens.common.ListContent
import com.example.newsappincompose.navigation.screens.common.NewsViewModel
import com.example.newsappincompose.navigation.screens.destinations.ArticleNavScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavoriteScreen(
    favoriteNewsViewModel: NewsViewModel,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            FavoriteTopBar()
        }
    ) {
        ListContent(
            items = favoriteNewsViewModel.getFavoriteNews.collectAsLazyPagingItems(),
            onArticleClicked = { navigator.navigate(ArticleNavScreenDestination(it)) }
        )
    }

}