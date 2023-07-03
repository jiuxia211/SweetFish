package com.example.sweetfish.ui.notifications

data class ChatListJsonData(
    val code: Int,
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val avatar: String,
    val id: Int,
    val username: String
)