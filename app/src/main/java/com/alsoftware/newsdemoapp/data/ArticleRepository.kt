package com.alsoftware.newsdemoapp.data

import androidx.lifecycle.LiveData
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.Source

interface ArticleRepository {
    fun getArticlesFromSource(sourceId: String): LiveData<List<Article>?>
    fun getArticlesByCategory(category: String):LiveData<List<Article>?>
    fun getSourcesByCategory(category: String):LiveData<List<Source>?>
}