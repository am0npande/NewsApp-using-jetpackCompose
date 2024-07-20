package com.example.kabar.presentation.bookmark

import com.example.kabar.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)