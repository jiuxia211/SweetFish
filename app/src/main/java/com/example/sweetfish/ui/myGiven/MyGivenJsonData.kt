package com.example.sweetfish.ui.myGiven

data class MyGivenJsonData(
    val code: Int,
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val avatar: String,
    val buy_price: Double,
    val content: String,
    val cover: String,
    val id: Int,
    val price: Double,
    val title: String,
    val username: String
)