package com.alsoftware.newsdemoapp.data.newsapi

import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("v2/everything")
    fun getArticles(@Query("apiKey") api_key: String,
                    @Query("q") q: String) : Call<ArticlesResponse>
}