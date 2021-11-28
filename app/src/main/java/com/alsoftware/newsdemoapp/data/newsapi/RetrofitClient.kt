package com.alsoftware.newsdemoapp.data.newsapi

import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val articlesApi: ArticlesApi

    companion object {
        private const val API_KEY = "f26a464e024a4d51bedc7a67d7e41ced"
        private const val NEWS_API_BASE_URL = "https://newsapi.org/"
    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        articlesApi = retrofit.create(ArticlesApi::class.java)
    }

    fun getArticles(): Call<ArticlesResponse> {
        return articlesApi.getArticles(API_KEY,"tech")
    }
}