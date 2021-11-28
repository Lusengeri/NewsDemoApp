package com.alsoftware.newsdemoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticlesResponse(
    @SerializedName("articles")
    @Expose
    val articles: List<Article>? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int? = null
)