package com.example.newsappincompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsappincompose.util.Constants.NEWS_REMOTE_KEYS_TABLE_NAME

@Entity(tableName = NEWS_REMOTE_KEYS_TABLE_NAME)
data class NewsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val nextPage: Int?,
    val prevPage: Int?
)
