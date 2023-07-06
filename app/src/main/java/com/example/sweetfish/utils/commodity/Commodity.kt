package com.example.sweetfish.utils.commodity

data class Commodity(
    val id: Int,
    val title: String,
    val coverPath: String,
    val avatarPath: String,
//    val imageId: Int,
//    val avatarId: Int,
    val username: String,
    val price: Double
)