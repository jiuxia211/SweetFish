package com.example.sweetfish.utils.socketEvent

data class MessageReceipt(
    val date: String,
    val from: Int,
    val id: Int,
    val message: String,
    val type: String
)