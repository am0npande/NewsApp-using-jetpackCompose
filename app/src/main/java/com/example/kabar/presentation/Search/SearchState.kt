package com.example.kabar.presentation.Search

import androidx.paging.PagingData
import com.example.kabar.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)