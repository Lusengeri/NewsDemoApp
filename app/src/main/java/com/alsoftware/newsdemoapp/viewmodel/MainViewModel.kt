package com.alsoftware.newsdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alsoftware.newsdemoapp.data.ArticleRepositoryImpl
import com.alsoftware.newsdemoapp.data.model.Article

class MainViewModel(
    private val articleRepository: ArticleRepositoryImpl = ArticleRepositoryImpl()) : ViewModel() {

    fun getArticles(): LiveData<List<Article>?> {
        return articleRepository.getArticles()
    }
}