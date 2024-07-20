package com.example.kabar.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kabar.domain.model.Article

//to able to fetch the news we want newsApi and newsSources
class NewsPagingSources(
    private val newsApi:NewsApi,
    private val sources: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let{
            val anchorPage = state.closestPageToPosition(it)//error...............................

            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    //this request from api and return article
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try{
            val newResponse = newsApi.getNewss(str = sources, Page = page)
            totalNewsCount += newResponse.articles.size
            val articles = newResponse.articles.distinctBy { it.title }//remove duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}