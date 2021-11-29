package com.alsoftware.newsdemoapp.data.newsapi

import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import com.alsoftware.newsdemoapp.data.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("v2/everything")
    fun getArticlesFromSource(@Query("apiKey") apiKey: String,
                              @Query("sources") sources: String,
                              @Query("sortBy") sortBy: String="publishedAt") : Call<ArticlesResponse>

    @GET("v2/top-headlines")
    fun getTopHeadlinesByCategory(@Query("apiKey") apiKey: String,
                                  @Query("category") category: String,
                                  ):  Call<ArticlesResponse>

    @GET("v2/top-headlines/sources")
    fun getSourcesByCategory(@Query("apiKey") apiKey: String,
                             @Query("category") category: String,
                             @Query("language") language: String="en"): Call<SourcesResponse>
}