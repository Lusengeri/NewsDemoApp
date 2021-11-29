package com.alsoftware.newsdemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.alsoftware.newsdemoapp.data.model.Article
import com.alsoftware.newsdemoapp.data.model.Source
import com.alsoftware.newsdemoapp.viewmodel.MainViewModel
import com.alsoftware.newsdemoapp.databinding.FragmentMainBinding

import kotlinx.android.synthetic.main.news_items_layout.view.*

class MainFragment : Fragment() {
    lateinit var mainViewModel : MainViewModel
    private val newsListAdapter = NewsListAdapter()
    private val sourceListAdapter=SourceListAdapter(this)
    private lateinit var articlesList : RecyclerView
    private lateinit var sourcesList: RecyclerView

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureDataSource()
        setUpArticles()
        setUpSources()
    }

    private fun configureDataSource() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        
        val newsListObserver = Observer<List<Article>?> { articles ->
            if (articles != null) {
                newsListAdapter.setList(articles)
            }
        }

        val sourcesListObserver = Observer<List<Source>?> { sources ->
            if (sources != null) {
                sourceListAdapter.setList(sources)
            }
        }
        mainViewModel.getTechnologySources().observe(viewLifecycleOwner, sourcesListObserver)
        mainViewModel.getArticles().observe(viewLifecycleOwner, newsListObserver)
    }

    private fun setUpArticles() {
        articlesList = binding.root.newsArticlesList
        articlesList.layoutManager = LinearLayoutManager(context)
        articlesList.adapter = newsListAdapter
        articlesList.itemAnimator = DefaultItemAnimator()
    }

    private fun setUpSources() {
        sourcesList = binding.root.sourcesList
        sourcesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        sourcesList.adapter = sourceListAdapter
        sourcesList.itemAnimator = DefaultItemAnimator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}