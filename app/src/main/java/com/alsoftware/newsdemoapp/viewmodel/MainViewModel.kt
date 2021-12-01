package com.alsoftware.newsdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alsoftware.newsdemoapp.data.ArticleRepositoryImpl
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.Source

class MainViewModel(
    private val articleRepository: ArticleRepositoryImpl = ArticleRepositoryImpl()) : ViewModel() {

    fun getArticlesFromSource(sourceId: String) :LiveData<List<Article>?>{
        return articleRepository.getArticlesFromSource(sourceId)
    }

    fun getArticlesByCategory(category: String): LiveData<List<Article>?> {
        return articleRepository.getArticlesByCategory(category)
    }

    fun getSourcesByCategory(category: String):LiveData<List<Source>?>{
        return articleRepository.getSourcesByCategory(category)
    }
}