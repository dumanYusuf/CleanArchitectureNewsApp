package com.example.newsapp

sealed class Screen(val route:String){

    object NewsScreen:Screen("newsPage")
    object DetailPage:Screen("detailPage")


}