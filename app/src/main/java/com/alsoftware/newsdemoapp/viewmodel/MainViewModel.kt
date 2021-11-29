package com.alsoftware.newsdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alsoftware.newsdemoapp.data.ArticleRepositoryImpl
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.Source

class MainViewModel(
    private val articleRepository: ArticleRepositoryImpl = ArticleRepositoryImpl()) : ViewModel() {
    private lateinit var currentArticleLiveData:MutableLiveData<List<Article>?>

    fun getArticlesFromSource(sourceId: String) :LiveData<List<Article>?>{
        return articleRepository.getArticlesFromSource(sourceId)
    }

    fun getArticles(): LiveData<List<Article>?> {

        return articleRepository.getTechnologyArticles()
    }

    fun getTechnologySources():LiveData<List<Source>?>{
        return articleRepository.getTechnologySources()
    }
}