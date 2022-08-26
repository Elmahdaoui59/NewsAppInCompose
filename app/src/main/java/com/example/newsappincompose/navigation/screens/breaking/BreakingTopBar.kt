package com.example.newsappincompose.navigation.screens.breaking

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BreakingTopBar() {
    TopAppBar(
        title = { Text(text = "Breaking News", color = Color.White) },
        backgroundColor = Color.Blue
    )
}