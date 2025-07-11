package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(private val newsRepository: NewsRepository) {

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}