package com.example.kabar.domain.usercases.news

import androidx.paging.PagingData
import com.example.kabar.domain.model.Article
import com.example.kabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
//paging 3 library is covered here....
class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getTheNews(source = sources)
    }
}