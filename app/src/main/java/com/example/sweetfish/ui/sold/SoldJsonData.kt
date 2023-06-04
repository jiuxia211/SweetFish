package com.example.sweetfish.ui.sold

import com.example.sweetfish.ui.purchased.Data

data class SoldJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val posts_lists: List<PostsLists>
)

data class PostsLists(
    val avatar: String,
    val buy_price: Double,
    val content: String,
    val cover: String,
    val id: Int,
    val price: Double,
    val title: String,
    val username: String
)