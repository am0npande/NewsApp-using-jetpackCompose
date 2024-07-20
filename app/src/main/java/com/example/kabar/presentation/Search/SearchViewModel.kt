package com.example.kabar.presentation.Search


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kabar.domain.usercases.news.NewsUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val newsUsesCases: NewsUsesCases
): ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state //public version of _state

    fun onEventss(event: SearchEvent){
        when(event){
            SearchEvent.SearchNews -> {
                searchNewss()
            }
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.

                copy(searchQuery = event.searchQuery)
            }
        }
    }

    private fun searchNewss() {
        val articles = newsUsesCases.seachNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news","abc-news","al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }


}