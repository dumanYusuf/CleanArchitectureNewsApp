package com.example.newsapp.data.repo

import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.dto.NewsAppDto
import com.example.newsapp.domain.repo.NewsRepo
import com.example.newsapp.util.Constans
import javax.inject.Inject


class NewsAppRepoImp @Inject constructor(private val api:NewsApi):NewsRepo{

    override suspend fun getNewsList(): NewsAppDto {
        return api.getNewsListe(Constans.API_KEY)
    }
}