package com.akshay.newsapp.news.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.newsapp.core.ui.ViewState
import com.akshay.newsapp.core.ui.base.BaseActivity
import com.akshay.newsapp.core.utils.observeNotNull
import com.akshay.newsapp.core.utils.toast
import com.akshay.newsapp.databinding.ActivityMainBinding
import com.akshay.newsapp.news.ui.adapter.NewsArticlesAdapter
import com.akshay.newsapp.news.ui.viewmodel.NewsArticleViewModel


class NewsActivity : BaseActivity() {

    private val newsArticleViewModel: NewsArticleViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        val adapter = NewsArticlesAdapter { toast("This is not done.") }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(this)

        newsArticleViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> adapter.submitList(state.data)
                is ViewState.Loading -> binding.newsList.showLoading()
                is ViewState.Error -> toast("Something went wrong ${state.message}")
            }
        }

    }
}
