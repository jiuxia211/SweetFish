package com.example.sweetfish.utils.msg

class ChatMsg(val content: String, val type: Int, val id: Int) {
    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}