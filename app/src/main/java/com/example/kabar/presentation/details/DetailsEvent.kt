package com.example.kabar.presentation.details

import com.example.kabar.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect : DetailsEvent()


}
