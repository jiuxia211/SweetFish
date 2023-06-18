package com.example.sweetfish.ui.login

data class UserInfo(
    val account: String,
    val password: String,
    val isRePassword: Boolean,
    val isAutoLogin: Boolean,
)