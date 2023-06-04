package com.example.sweetfish.ui.space

data class SetAvatarJsonData(
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val avatar: String
)