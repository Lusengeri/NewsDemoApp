package com.alsoftware.newsdemoapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.ArticlesResponse
import com.alsoftware.newsdemoapp.data.newsapi.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ArticleRepositoryImpl : ArticleRepository {
    private val retrofitClient = RetrofitClient()

    override fun getArticles(): LiveData<List<Article>?> {
        val data = MutableLiveData<List<Article>>()

        retrofitClient.getArticles().enqueue(
            object: Callback<ArticlesResponse> {
                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    data.value = null
                    Log.d(this.javaClass.simpleName,"Failure:" + t.message)
                }

                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    data.value = response.body()?.articles
                    if (!response.isSuccessful) {
                        Log.d(this.javaClass.simpleName, "Response Error: ${response.code()}")
                    } else {
                        Log.d(this.javaClass.simpleName, "Response: ${response.body()?.articles}")
                    }
                }
            }
        )
        return data
    }
}