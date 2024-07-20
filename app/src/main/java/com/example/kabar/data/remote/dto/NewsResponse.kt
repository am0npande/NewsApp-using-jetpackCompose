package com.example.kabar.data.remote.dto

import com.example.kabar.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)