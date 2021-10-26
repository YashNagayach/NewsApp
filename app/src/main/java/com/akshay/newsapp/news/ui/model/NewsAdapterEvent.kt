package com.akshay.newsapp.news.ui.model

import com.akshay.newsapp.news.ui.adapter.NewsArticlesAdapter

sealed class NewsAdapterEvent {
    object ClickEvent : NewsAdapterEvent()
}
