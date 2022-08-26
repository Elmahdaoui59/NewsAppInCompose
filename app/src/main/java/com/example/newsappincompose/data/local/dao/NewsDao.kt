package com.example.newsappincompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newsappincompose.model.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articles: List<Article>)

    @Query("SELECT * FROM articles")
    fun getNews(): PagingSource<Int, Article>

    @Query("DELETE FROM articles")
    suspend fun deleteNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArticle(article: Article)

    @Query("SELECT * FROM articles WHERE favorite")
    fun getFavoriteNews(): PagingSource<Int, Article>
}