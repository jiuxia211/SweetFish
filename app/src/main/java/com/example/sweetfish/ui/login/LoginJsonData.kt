package com.example.sweetfish.ui.login

data class LoginJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val avatar: Any,
    val id: Int,
    val token: String,
    val username: String
)