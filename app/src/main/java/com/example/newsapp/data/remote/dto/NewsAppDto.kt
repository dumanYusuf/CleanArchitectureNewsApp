package com.example.newsapp.data.remote.dto

import com.example.newsapp.domain.model.Article

data class NewsAppDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

fun NewsAppDto.toNewsList(): List<Article> {
    return articles.map { articleDto ->
        Article(
            author = articleDto.author ?: "unknown",
            content = articleDto.content ?: "",
            description = articleDto.description ?: "",
            publishedAt = articleDto.publishedAt ?: "",
            title = articleDto.title ?: "",
            url = articleDto.url ?: "",
            urlToImage = articleDto.urlToImage ?: ""
        )
    }
}