package com.example.kabar.domain.usercases.news

data class NewsUsesCases(
    val getNews: GetNews,
    val seachNews: SeachNews,
    val deleteArticle:DeleteArticle,
    val selectArticle: SelectArticle, //url
    val upsertArticle: UpsertArticle,
    val selArticles: SelArticles  //flow article
)
