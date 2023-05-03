package com.example.sweetfish.ui.purchased

data class PurchasedJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val posts_lists: List<PostsLists>
)

data class PostsLists(
    val buy_price: Double,
    val content: String,
    val id: Int,
    val is_approved: Int,
    val price: Double,
    val title: String
)