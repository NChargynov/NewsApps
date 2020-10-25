package com.example.newsapps.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Source(
    var id: String?,
    var name: String?
) : Serializable

@Entity
data class Article(
//    var source: Source?,
    var author: String,
    @PrimaryKey(autoGenerate = false)
    var title: String,
    var description: String,
    var url: String,
    var publishedAt: String,
    var content: String,
    var urlToImage: String,
    var isFavorite: Boolean = false
) : Serializable