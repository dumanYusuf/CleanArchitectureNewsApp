package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.news.views.NewsScreen
import com.example.newsapp.presentation.newsDetail.views.DetailPage
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                   ControllerNav()
                }
            }
        }
    }
}

@Composable
fun ControllerNav() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
        composable(Screen.NewsScreen.route){
            NewsScreen(navController = navController)
        }
        composable(Screen.DetailPage.route+"/{news}",
            arguments = listOf(
                navArgument("news"){type= NavType.StringType}
            )
        ){
            val jsonNews = it.arguments?.getString("news")
            val decodedJsonNews = URLDecoder.decode(jsonNews, "UTF-8")
            val news = Gson().fromJson(decodedJsonNews, Article::class.java)
            DetailPage(news)
        }
    }
}

