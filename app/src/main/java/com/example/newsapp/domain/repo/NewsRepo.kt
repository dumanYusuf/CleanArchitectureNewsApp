package com.example.newsapp.domain.repo

import com.example.newsapp.data.remote.dto.NewsAppDto


interface NewsRepo {

    suspend fun getNewsList():NewsAppDto
}