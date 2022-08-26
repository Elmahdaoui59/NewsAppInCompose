package com.example.newsappincompose.navigation.screens.common

import android.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsappincompose.navigation.screens.destinations.BreakingScreenDestination
import com.example.newsappincompose.navigation.screens.destinations.FavoriteScreenDestination
import com.example.newsappincompose.navigation.screens.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String,
    val iconColor: Int
) {
    Breaking(BreakingScreenDestination, Icons.Default.Info, "breaking", Color.YELLOW),
    Search(SearchScreenDestination, Icons.Default.Search, "search", Color.CYAN),
    Favorite(FavoriteScreenDestination, Icons.Default.Favorite, "favorite", Color.RED)

}