package com.example.sweetfish.ui.my

class UserJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val avatar: String,
    val background: String,
    val balance: Float,
    val follow: Int,
    val followed: Int,
    val id: Int,
    val mail: String,
    val permission: Int,
    val real: Int,
    val turnover: Int,
    val username: String,
    val isFollowed: Int
)
