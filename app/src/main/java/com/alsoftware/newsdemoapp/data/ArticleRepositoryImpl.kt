package com.alsoftware.newsdemoapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import com.alsoftware.newsdemoapp.data.model.Source
import com.alsoftware.newsdemoapp.data.model.SourcesResponse
import com.alsoftware.newsdemoapp.data.newsapi.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ArticleRepositoryImpl : ArticleRepository {
    private val retrofitClient = RetrofitClient()
    private val articleData = MutableLiveData<List<Article>>()

    override fun getArticlesFromSource(sourceId:String): LiveData<List<Article>?> {


        retrofitClient.getArticlesFromSource(sourceId).enqueue(
            object: Callback<ArticlesResponse> {
                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    articleData.value = null
                    Log.d(this.javaClass.simpleName,"Failure:" + t.message)
                }

                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    articleData.value = response.body()?.articles
                    if (!response.isSuccessful) {
                        Log.d(this.javaClass.simpleName, "Response Error: ${response.code()}")
                    } else {
                        Log.d(this.javaClass.simpleName, "Response: ${response.body()?.articles}")
                    }
                }
            }
        )
        return articleData
    }

    override fun getTechnologyArticles(): LiveData<List<Article>?> {


        retrofitClient.getTechnologyArticles().enqueue(
            object: Callback<ArticlesResponse> {
                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    articleData.value = null
                    Log.d(this.javaClass.simpleName,"Failure:" + t.message)
                }

                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    articleData.value = response.body()?.articles
                    if (!response.isSuccessful) {
                        Log.d(this.javaClass.simpleName, "Response Error: ${response.code()}")
                    } else {
                        Log.d(this.javaClass.simpleName, "Response: ${response.body()?.articles}")
                    }
                }
            }
        )
        return articleData
    }

    override fun getTechnologySources(): LiveData<List<Source>?> {

        val data = MutableLiveData<List<Source>>()

        retrofitClient.getTechnologySources().enqueue(
            object: Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    data.value = response.body()?.sources
                    if (!response.isSuccessful) {
                        Log.d(this.javaClass.simpleName, "Response Error: ${response.code()}")
                    } else {
                        Log.d(this.javaClass.simpleName, "Response: ${response.body()?.sources}")
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    data.value = null
                    Log.d(this.javaClass.simpleName,"Failure:" + t.message)
                }
            }
        )
        return data
    }
}