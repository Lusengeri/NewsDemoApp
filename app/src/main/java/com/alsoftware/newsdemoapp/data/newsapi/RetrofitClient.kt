package com.alsoftware.newsdemoapp.data.newsapi

import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import com.alsoftware.newsdemoapp.data.model.SourcesResponse

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.alsoftware.newsdemoapp.GlobalApplication

class RetrofitClient {
    private val articlesApi: ArticlesApi

    companion object {
        private val ai: ApplicationInfo = GlobalApplication.context.packageManager
        .getApplicationInfo(GlobalApplication.context.packageName, PackageManager.GET_META_DATA)
        private val value = ai.metaData["keyValue"]
        private val API_KEY=value.toString() //=
        private const val NEWS_API_BASE_URL = "https://newsapi.org/"
    }

    init {
        /*
        val ai: ApplicationInfo = (GlobalApplication.context.packageManager
            .getApplicationInfo(GlobalApplication.context.packageName, PackageManager.GET_META_DATA)as ApplicationInfo)
        val value = ai.metaData["keyValue"]

        API_KEY = value.toString()

         */
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        articlesApi = retrofit.create(ArticlesApi::class.java)
    }

    fun getArticlesFromSource(sourceId: String): Call<ArticlesResponse> {
        return articlesApi.getArticlesFromSource(API_KEY,sourceId)
    }

    fun getArticlesByCategory(category: String):Call<ArticlesResponse>{
        return articlesApi.getTopHeadlinesByCategory(API_KEY,category)
    }

    fun getSourcesByCategory(category: String):Call<SourcesResponse>{
        return articlesApi.getSourcesByCategory(API_KEY, category)
    }
}