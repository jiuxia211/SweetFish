package com.example.sweetfish.ui.register

data class RegisterJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val id: Int,
    val username: String
)