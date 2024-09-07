package com.example.newsapp.presentation.newsDetail.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.domain.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPage(
    news:Article
) {

    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text=news.author)})
        },
        content = {innerPadding->
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)){
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = rememberAsyncImagePainter(model =news.urlToImage , imageLoader = ImageLoader(context) ),
                    contentDescription ="", contentScale = ContentScale.Crop )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Title:${news.title}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Description:${news.description}", fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Date:${news.publishedAt}", fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            }
        }
    )
}