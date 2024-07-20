package com.example.kabar.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kabar.domain.model.Article
import com.example.kabar.presentation.Dimes.ExtraSmallPadding2
import com.example.kabar.presentation.Dimes.mediumPadding1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles:LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingesult(articles = articles)
    if(handlePagingResult){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ){
            items(count = articles.itemCount){
                articles[it]?.let{
                    ArticleCard(article = it, onClick ={ onClick(it) } )
                }
            }
        }
    }
}
//there are 3 types of .loadstate
//referesh
//append
//prebend
@Composable
fun handlePagingesult(
    articles:LazyPagingItems<Article>,
):Boolean{
    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error

        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen()
            false
        }

        else -> {true}
    }
}

@Composable
fun ShimmerEffect(){
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding1)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding1)
            )
        }
    }

}



@Composable
fun ArticleBookMARKList(
    modifier: Modifier = Modifier,
    articles:List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(count = articles.size){
            val article = articles[it]
            ArticleCard(article = article, onClick = {onClick(article)})
        }

    }
}
