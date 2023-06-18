package com.example.sweetfish.ui.main

data class RecommendJsonData(
    val code: String,
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val avatar: String,
    val content: String,
    val cover: String,
    val fav: Int,
    val post_id: Int,
    val price: Double,
    val title: String,
    val username: String
)