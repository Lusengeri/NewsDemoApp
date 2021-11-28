package com.alsoftware.newsdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureDataSource()
    }

    private fun configureDataSource() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<List<Article>?> { articles ->
            if (articles == null) {
                findViewById<TextView>(R.id.starterText).text = "Error fetching the news"
            } else {
                findViewById<TextView>(R.id.starterText).text = articles[1].title
            }
        }

        mainViewModel.getArticles().observe(this, observer)
    }
}