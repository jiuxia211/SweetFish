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
    val content: String,
    val id: Int,
    val title: String,
    val user_id: Int
)