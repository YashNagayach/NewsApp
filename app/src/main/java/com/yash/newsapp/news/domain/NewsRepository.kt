package com.yash.newsapp.news.domain

import com.yash.newsapp.core.ui.ViewState
import com.yash.newsapp.core.utils.httpError
import com.yash.newsapp.news.NewsMapper
import com.yash.newsapp.news.api.NewsResponse
import com.yash.newsapp.news.api.NewsService
import com.yash.newsapp.news.storage.NewsArticlesDao
import com.yash.newsapp.news.storage.entity.NewsArticleDb
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepository {

    fun getNewsArticles(): Flow<ViewState<List<NewsArticleDb>>>

    suspend fun getNewsFromWebservice(): Response<NewsResponse>
}

@Singleton
class DefaultNewsRepository @Inject constructor(
    private val newsDao: NewsArticlesDao,
    private val newsService: NewsService
) : NewsRepository, NewsMapper {

    override fun getNewsArticles(): Flow<ViewState<List<NewsArticleDb>>> = flow {
        emit(ViewState.loading())

        val freshNews = getNewsFromWebservice()
        freshNews.body()?.articles?.toStorage()?.let(newsDao::clearAndCacheArticles)

        val cachedNews = newsDao.getNewsArticles()
        emitAll(cachedNews.map { ViewState.success(it) })
    }
        .flowOn(Dispatchers.IO)

    override suspend fun getNewsFromWebservice(): Response<NewsResponse> {
        return try {
            newsService.getTopHeadlines()
        } catch (e: Exception) {
            httpError(404)
        }
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface NewsRepositoryModule {
    @Binds
    fun it(it: DefaultNewsRepository): NewsRepository
}
