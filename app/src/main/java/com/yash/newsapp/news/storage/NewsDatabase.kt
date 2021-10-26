package com.yash.newsapp.news.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yash.newsapp.news.storage.entity.NewsArticleDb

@Database(
        entities = [NewsArticleDb::class],
        version = 1
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsArticlesDao(): NewsArticlesDao

    companion object {

        private const val databaseName = "news-db"

        fun buildDefault(context: Context) =
                Room.databaseBuilder(context, NewsDatabase::class.java, databaseName)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
