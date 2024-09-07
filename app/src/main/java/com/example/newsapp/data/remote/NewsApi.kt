package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.NewsAppDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


  /*  @GET("/v2/everything?q=tesla&from=2024-08-07&sortBy=publishedAt&apiKey=4f1dbd15ec5343f48b519ebfbf825b90")
    suspend fun getNewsList():Flow<List<NewsAppDto>>*/

    @GET("/v2/everything?q=tesla&from=2024-08-07&sortBy=publishedAt&apiKey=4f1dbd15ec5343f48b519ebfbf825b90")
   //@GET("/v2/everything")
    suspend fun getNewsListe(
        @Query("key") key:String
    ):NewsAppDto



}