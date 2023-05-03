package com.example.sweetfish.ui.my

data class UserJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val avatar: String,
    val balance: Int,
    val id: Int,
    val mail: String,
    val permission: Int,
    val username: String
)