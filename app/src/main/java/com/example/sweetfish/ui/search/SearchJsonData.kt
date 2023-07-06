package com.example.sweetfish.ui.search

data class SearchJsonData(
    val code: Int,
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val avatar: String,
    val followed: Int,
    val id: Int,
    val turnover: Int,
    val username: String,
    val buy_price: Double,
    val content: String,
    val cover: String,
    val fav: Int,
    val now_buyer: Int,
    val pic_urls: List<String>,
    val post_id: Int,
    val price: Double,
    val title: String,
    val user_id: Int,
)