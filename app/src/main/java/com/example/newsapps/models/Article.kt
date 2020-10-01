package com.example.newsapps.models

import java.io.Serializable

data class Source(
    var id: String?,
    var name: String?
) : Serializable

data class Article(
    var source: Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var publishedAt: String?,
    var content: String?,
    var urlToImage: String?
) : Serializable