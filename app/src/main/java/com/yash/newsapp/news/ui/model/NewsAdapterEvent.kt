package com.yash.newsapp.news.ui.model

sealed class NewsAdapterEvent {
    object ClickEvent : NewsAdapterEvent()
}
