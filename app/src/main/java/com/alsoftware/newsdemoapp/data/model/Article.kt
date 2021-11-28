package com.alsoftware.newsdemoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    @Expose
    val author: String? = null,

    @SerializedName("content")
    @Expose
    val content: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null,

    @SerializedName("source")
    @Expose
    val source: Source? = null,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String
)