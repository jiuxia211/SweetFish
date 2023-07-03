package com.example.sweetfish.ui.chat

data class ChatHistoryJsonData(
    val code: Int,
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val from: Int,
    val message: String,
    val time: String,
    val to: Int
)