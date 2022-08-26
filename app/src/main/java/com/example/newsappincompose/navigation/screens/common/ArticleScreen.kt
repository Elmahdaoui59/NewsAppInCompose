package com.example.newsappincompose.navigation.screens.common

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.example.newsappincompose.model.Article
import com.example.newsappincompose.ui.theme.Black
import com.example.newsappincompose.ui.theme.Red
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ArticleNavScreen(article: Article, viewModel: NewsViewModel) {
    ArticleScreen(
        article = article
    ) { viewModel.updateArticle(it.copy(favorite = true)) }

}


@Composable
fun ArticleScreen(
    article: Article,
    makeFavorite: (Article) -> Unit
) {

    val fabColor = rememberSaveable {
        mutableStateOf(Black.toArgb())
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    makeFavorite(article)
                    fabColor.value = Red.toArgb()
                },
                shape = CircleShape,
                contentColor = Color(fabColor.value),
            ) { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "") }
        }

    ) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    WebViewClient()
                    loadUrl(article.url)
                }
            },
            update = { it.loadUrl(article.url) }
        )
    }
}
