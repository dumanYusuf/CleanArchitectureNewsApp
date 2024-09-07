package com.example.newsapp.domain.use_case.getNews

import android.util.Log
import com.example.newsapp.data.remote.dto.toNewsList
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repo.NewsRepo
import com.example.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repo: NewsRepo) {

    fun getNewsApp(): Flow<Resource<List<Article>>> = flow {

        try {
            emit(Resource.Loading())
            val listNews = repo.getNewsList()
            if (listNews.articles.isNotEmpty()) {
                emit(Resource.Success(listNews.toNewsList()))
            } else {
                emit(Resource.Error("Error: No articles found"))
                Log.e("NewsUseCase", "Error: No articles found")
            }
        } catch (e: Exception) {
            val errorMessage = "Error: ${e.localizedMessage ?: "Unknown error"}"
            emit(Resource.Error(errorMessage))
            Log.e("NewsUseCase", errorMessage, e) // Hata mesajını ve istisna izini logla
        }
    }
}
