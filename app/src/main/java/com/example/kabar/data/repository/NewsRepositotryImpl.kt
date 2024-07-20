package com.example.kabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kabar.data.local.NewsDao
import com.example.kabar.data.remote.NewsApi
import com.example.kabar.data.remote.NewsPagingSources
import com.example.kabar.data.remote.SearchNewsPagingSource
import com.example.kabar.domain.model.Article
import com.example.kabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositotryImpl(
    private val newApi:NewsApi,
    private val newsDao: NewsDao
): NewsRepository {

    override fun getTheNews(source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSources(
                    newsApi = newApi,
                    sources = source.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsapi = newApi,
                    sources = source.joinToString(separator = ","),
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticle(): Flow<List<Article>> {
       return newsDao.getArticles().onEach { it.reversed() }
    }

    override suspend fun selArticles(url: String): Article? {
        return newsDao.getArticle(url)
    }

}