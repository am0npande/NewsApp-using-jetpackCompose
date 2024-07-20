package com.example.kabar.presentation.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kabar.domain.model.Article
import com.example.kabar.presentation.Dimes.mediumPadding1
import com.example.kabar.presentation.Dimes.mediumPadding2
import com.example.kabar.presentation.Dimes.topPadding
import com.example.kabar.presentation.common.ArticleList
import com.example.kabar.presentation.common.SearchBar
import com.example.kabar.presentation.nvGraph.Route
import java.io.StringWriter

@Composable
fun SearchScreen(
    state:SearchState,
    event: (SearchEvent) ->Unit,
    navigateToDetails:(Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = mediumPadding1,
                end = mediumPadding2
            )
            .statusBarsPadding()
    ){
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )

        Spacer(modifier = Modifier.height(mediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles =articles, onClick = {navigateToDetails(it)})
        }
    }
}