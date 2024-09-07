package com.example.newsapp.presentation.news

import com.example.newsapp.domain.model.Article

data class NewsState(
    val newsList:List<Article> = emptyList(),
    val isLoading:Boolean =false,
    val errorMessage:String =""
)