package com.example.kabar.domain.usercases.news

import androidx.paging.PagingData
import com.example.kabar.domain.model.Article
import com.example.kabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SeachNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery:String,sources:List<String>): Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery,source = sources)
    }
}