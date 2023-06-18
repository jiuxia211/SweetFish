package com.example.sweetfish.ui.management

data class GetAuditJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val post_list: List<Post>
)

data class Post(
    val avatar: String,
    val buy_price: Int,
    val content: String,
    val cover: String,
    val id: Int,
    val price: Double,
    val title: String,
    val username: String
)