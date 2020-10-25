package com.example.newsapps.models

data class ResponseBody(
    var status: String?,
    var totalResults: Int?,
    var articles: MutableList<Article>,
    var message: String?,
    var code: String?
)