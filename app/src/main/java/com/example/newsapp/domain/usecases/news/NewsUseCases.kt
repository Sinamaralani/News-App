package com.example.newsapp.domain.usecases.news

data class NewsUseCases(
    val searchNews: SearchNews,
    val getNews: GetNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)
