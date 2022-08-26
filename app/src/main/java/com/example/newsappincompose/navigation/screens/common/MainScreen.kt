package com.example.newsappincompose.navigation.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.newsappincompose.navigation.screens.NavGraphs
import com.example.newsappincompose.navigation.screens.destinations.ArticleNavScreenDestination
import com.example.newsappincompose.navigation.screens.destinations.BreakingScreenDestination
import com.example.newsappincompose.navigation.screens.destinations.FavoriteScreenDestination
import com.example.newsappincompose.navigation.screens.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(PaddingValues(0.dp,0.dp,0.dp,innerPadding.calculateBottomPadding()))) {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(ArticleNavScreenDestination) {
                        hiltViewModel<NewsViewModel>()
                    }
                    dependency(BreakingScreenDestination) {
                        hiltViewModel<NewsViewModel>()
                    }
                    dependency(FavoriteScreenDestination) {
                        hiltViewModel<NewsViewModel>()
                    }
                    dependency(SearchScreenDestination) {
                        hiltViewModel<NewsViewModel>()
                    }
                }
            )
        }

    }
}