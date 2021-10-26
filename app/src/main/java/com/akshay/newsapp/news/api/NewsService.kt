package com.akshay.newsapp.news.api

import com.akshay.newsapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?apiKey=${BuildConfig.NEWS_API_KEY}&category=technology")
    suspend fun getTopHeadlines(): Response<NewsResponse>

}
