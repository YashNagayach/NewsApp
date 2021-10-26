package com.yash.newsapp.news

import com.yash.newsapp.core.mapper.Mapper
import com.yash.newsapp.news.api.NewsArticle
import com.yash.newsapp.news.storage.entity.NewsArticleDb

interface NewsMapper : Mapper<NewsArticleDb, NewsArticle> {
    override fun NewsArticleDb.toRemote(): NewsArticle {
        return NewsArticle(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            source = NewsArticle.Source(source.id, source.name)
        )
    }

    override fun NewsArticle.toStorage(): NewsArticleDb {
        return NewsArticleDb(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content,
            source = NewsArticleDb.Source(source.id, source.name)
        )
    }
}
