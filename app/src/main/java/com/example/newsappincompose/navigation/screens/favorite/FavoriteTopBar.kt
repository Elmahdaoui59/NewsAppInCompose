package com.example.newsappincompose.navigation.screens.favorite

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteTopBar() {
    TopAppBar(
        title = { Text(text = "Favorite News", color = Color.White)},
        backgroundColor = Color.Blue
    )
}