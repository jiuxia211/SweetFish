package com.example.sweetfish.ui.detail

data class DetailJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val detail: Detail
)

data class Detail(
    val avatar: String,
    val buy_price: Double,
    val content: String,
    val cover: String,
    val fav: Int,
    val isFav: Int,
    val now_buyer: Int,
    val pic_urls: List<String>,
    val post_id: Int,
    val price: Double,
    val title: String,
    val user_id: Int,
    val username: String
)