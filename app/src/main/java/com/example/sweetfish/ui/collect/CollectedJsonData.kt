package com.example.sweetfish.ui.collect

data class CollectedJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val fav_list: List<Fav>
)

data class Fav(
    val avatar: String,
    val content: String,
    val cover: String,
    val fav: Int,
    val post_id: Int,
    val price: Double,
    val title: String,
    val username: String
)