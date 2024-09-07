package com.example.newsapp.data.di

import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repo.NewsAppRepoImp
import com.example.newsapp.domain.repo.NewsRepo
import com.example.newsapp.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesNews():NewsApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL)
            .build().create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNwesRepo(api:NewsApi):NewsRepo{
        return NewsAppRepoImp(api)
    }


}