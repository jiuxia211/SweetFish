package com.example.sweetfish.utils.msg

import androidx.recyclerview.widget.DiffUtil

class ChatMsgDiffCallback(
    private val oldChatList: List<ChatMsg>,
    private val newChatList: List<ChatMsg>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChatList[oldItemPosition].content == newChatList[newItemPosition].content
    }

    override fun getNewListSize(): Int {
        return newChatList.size
    }

    override fun getOldListSize(): Int {
        return oldChatList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChatList[oldItemPosition].content == newChatList[newItemPosition].content
    }
}