package com.example.newsapps.models

import java.io.Serializable

data class ResponseBody(
    var status: String?,
    var totalResults: Int?,
    var articles: MutableList<Article>,
    var message: String?,
    var code: String?
)