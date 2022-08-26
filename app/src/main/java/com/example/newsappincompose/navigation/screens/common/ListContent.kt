package com.example.newsappincompose.navigation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.newsappincompose.R
import com.example.newsappincompose.model.Article


@Composable
fun ListContent(
    items: LazyPagingItems<Article>,
    onArticleClicked: (Article) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        items(
            items = items,
            key = { item: Article ->
                item.id
            }
        ) { article ->
            article?.let { ArticleItem(article = article, onArticleClicked) }
        }
    }
}


@Composable
fun ArticleItem(article: Article, onArticleClicked: (Article) -> Unit) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = article.urlToImage).apply(block = fun ImageRequest.Builder.() {
            crossfade(durationMillis = 1000)
            error(R.drawable.ic_broken_image)
            placeholder(R.drawable.ic_placeholder)
        }).build()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onArticleClicked(article) },
        contentAlignment = Alignment.TopStart
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(1f)
                    .padding(5.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "article image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )

                Text(text = article.publishedAt.toString())
            }
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = article.title,
                    fontWeight = Bold,
                    modifier = Modifier.padding(vertical = 5.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = article.content ?: "",
                    style = MaterialTheme.typography.body2,
                    maxLines = 4,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
    }
}
