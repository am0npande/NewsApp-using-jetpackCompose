package com.example.kabar.data.remote

import com.example.kabar.data.remote.dto.NewsResponse
import com.example.kabar.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNewss(
        @Query("page")
        Page:Int,
        @Query("sources")
        str: String,
        @Query("apikey")
        apiKey:String = API_KEY

    ):NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery:String,
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey: String = API_KEY,
    ):NewsResponse


    @GET("everything")
    suspend fun CountryNews(
        @Query("country") Country:String,
        @Query("page") page:Int,
        @Query("sources") str: String,
        @Query("apikey") apiKey:String = API_KEY

    ):NewsResponse

}