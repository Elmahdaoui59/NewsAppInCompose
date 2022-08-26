package com.example.newsappincompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsappincompose.util.Constants.ARTICLE_TABLE_NAME
import kotlinx.serialization.Serializable
import java.util.Objects

@Entity(tableName = ARTICLE_TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    var source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String?,
    var favorite: Boolean?


) : java.io.Serializable {
    override fun hashCode(): Int {
        return Objects.hash(
            id, content, description, publishedAt, source?.name, title, url, urlToImage, favorite
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (id != other.id) return false
        if (content != other.content) return false
        if (description != other.description) return false
        if (publishedAt != other.publishedAt) return false
        if (source != other.source) return false
        if (title != other.title) return false
        if (url != other.url) return false
        if (urlToImage != other.urlToImage) return false
        if (favorite != other.favorite) return false

        return true
    }
}

