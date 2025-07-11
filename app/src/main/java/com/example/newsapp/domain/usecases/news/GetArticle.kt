package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class GetArticle(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticle(url)
    }
}