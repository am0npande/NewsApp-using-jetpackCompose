package com.example.kabar.domain.repository


import androidx.paging.PagingData
import com.example.kabar.domain.model.Article
import kotlinx.coroutines.flow.Flow

//paging is the technic to ask chunks of data from server
interface NewsRepository {

    fun getTheNews(source: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String,source: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticle(): Flow<List<Article>>

    suspend fun selArticles(url: String): Article?

}

