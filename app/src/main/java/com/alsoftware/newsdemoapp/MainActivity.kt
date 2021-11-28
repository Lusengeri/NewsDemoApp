package com.alsoftware.newsdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel : MainViewModel
    private lateinit var articlesList : RecyclerView
    private val adapter = NewsListAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureDataSource()

        articlesList = findViewById(R.id.newsArticlesList)
        articlesList.layoutManager = LinearLayoutManager(this.applicationContext)
        articlesList.adapter = adapter
        articlesList.itemAnimator = DefaultItemAnimator()
    }

    private fun configureDataSource() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<List<Article>?> { articles ->
            if (articles == null) {
                //findViewById<TextView>(R.id.starterText).text = "Error fetching the news"
            } else {
                adapter.setList(articles.subList(1,articles.lastIndex));
                setMainArticle(articles[0])
            }
        }
        mainViewModel.getArticles().observe(this, observer)
    }

    private fun setMainArticle(firstArticle : Article) {
        Picasso.get().load(firstArticle.urlToImage).into(findViewById<ImageView>(R.id.mainArticleImage))
        findViewById<TextView>(R.id.mainArticleTitle).text = firstArticle.title
        findViewById<TextView>(R.id.mainArticleAuthor).text = firstArticle.author
        findViewById<TextView>(R.id.mainArticleSource).text = firstArticle.source?.name
        findViewById<TextView>(R.id.mainArticlePublishDate).text =
            " - "+firstArticle.publishedAt?.substring(0,10)
        findViewById<TextView>(R.id.mainArticleSummary).text = firstArticle.description
    }
}