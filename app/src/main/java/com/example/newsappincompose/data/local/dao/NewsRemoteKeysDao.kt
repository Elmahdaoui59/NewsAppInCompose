package com.example.newsappincompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappincompose.model.NewsRemoteKeys

@Dao
interface NewsRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys : List<NewsRemoteKeys>)

    @Query("SELECT * FROM news_remote_keys WHERE id = :articleId")
    suspend fun getNewsRemoteKey(articleId: Long): NewsRemoteKeys

    @Query("DELETE FROM news_remote_keys")
    suspend fun deleteRemoteKeys()
}