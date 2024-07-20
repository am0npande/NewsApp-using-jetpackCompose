package com.example.kabar.domain.usercases.news

import com.example.kabar.data.local.NewsDao
import com.example.kabar.domain.model.Article
import com.example.kabar.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}