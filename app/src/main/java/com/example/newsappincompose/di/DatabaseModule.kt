package com.example.newsappincompose.di

import android.content.Context
import androidx.room.Room
import com.example.newsappincompose.data.local.NewsDatabase
import com.example.newsappincompose.util.Constants.NEWS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java, NEWS_DATABASE
        ).build()
    }
}