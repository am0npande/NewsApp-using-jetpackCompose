package com.example.kabar.domain.usercases.news

import com.example.kabar.data.local.NewsDao
import com.example.kabar.domain.model.Article
import com.example.kabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticle(

    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticle()
    }
}