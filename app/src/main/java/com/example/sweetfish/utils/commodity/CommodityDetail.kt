package com.example.sweetfish.utils.commodity

data class CommodityDetail(
    val id: Int,
    val title: String,
    val content: String,
    val price: String,
    val coverPath: String,
    val avatarPath: String,
//    val imageId: Int,
//    val avatarId: Int,
    val username: String,
    val pic_urls: List<String>,
)