package com.example.sweetfish.utils.socketEvent

data class Message(
    val from: Int,
    val message: String,
    val to: Int,
)