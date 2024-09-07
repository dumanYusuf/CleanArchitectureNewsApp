package com.example.newsapp.presentation.news

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.use_case.getNews.NewsUseCase
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: NewsUseCase):ViewModel() {

    private val _state= mutableStateOf(NewsState())
    val state:State<NewsState> = _state

    private var job:Job ?=null

    init {
        getNews()
    }

    private fun getNews(){
        Log.e("start viewModel","Start")
        job?.cancel()
        job=useCase.getNewsApp().onEach {
            when(it){
                is Resource.Success->{
                    _state.value=NewsState(newsList = it.data ?: emptyList())
                    Log.e("sucesss","${it.data}")

                }
                is Resource.Loading->{
                    _state.value= NewsState(isLoading = true)
                    Log.e("loadding","${it}")
                }
                is Resource.Error->{
                    _state.value= NewsState(errorMessage = it.message ?:"Error")
                    Log.e("error","${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

}