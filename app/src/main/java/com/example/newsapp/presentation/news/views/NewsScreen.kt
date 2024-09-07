package com.example.newsapp.presentation.news.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.Screen
import com.example.newsapp.presentation.news.NewsViewModel
import com.google.gson.Gson
import java.net.URLEncoder


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel= hiltViewModel()
) {
    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "News App") })
        },
        content = {innerPadding->
              LazyColumn(modifier = Modifier
                  .fillMaxSize()
                  .padding(innerPadding)) {
                  items(viewModel.state.value.newsList){news->
                      Card(modifier = Modifier.fillMaxWidth()) {
                          Row (modifier = Modifier.fillMaxWidth().padding(5.dp).clickable {
                              val charecterObject = Gson().toJson(news)
                              val encodedMovieObject = URLEncoder.encode(charecterObject, "UTF-8")
                              navController.navigate(Screen.DetailPage.route+"/$encodedMovieObject")
                          },
                              verticalAlignment = Alignment.CenterVertically){
                              Image(
                                  modifier = Modifier.size(150.dp),
                                  painter = rememberAsyncImagePainter(model =news.urlToImage , imageLoader = ImageLoader(context) ),
                                  contentDescription ="", contentScale = ContentScale.Crop )

                              Text(
                                  modifier = Modifier.padding(horizontal = 5.dp),
                                  text =news.title , fontSize = 20.sp)
                          }
                      }
                  }
              }
        }
    )
}