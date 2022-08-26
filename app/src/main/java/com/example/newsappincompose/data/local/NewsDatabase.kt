package com.example.newsappincompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsappincompose.data.local.dao.NewsDao
import com.example.newsappincompose.data.local.dao.NewsRemoteKeysDao
import com.example.newsappincompose.model.Article
import com.example.newsappincompose.model.NewsRemoteKeys

@Database(entities = [Article::class, NewsRemoteKeys::class], version = 2)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase(){
    abstract fun newsDao(): NewsDao
    abstract fun newsRemoteKeysDao(): NewsRemoteKeysDao
}