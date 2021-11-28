package com.alsoftware.newsdemoapp.data

import androidx.lifecycle.LiveData
import com.alsoftware.newsdemoapp.data.model.Article

interface ArticleRepository {
    fun getArticles(): LiveData<List<Article>?>
}