package com.example.sweetfish.utils.user

import androidx.recyclerview.widget.DiffUtil

class ChatListUserDiffCallback(
    private val oldChatList: List<ChatListUser>,
    private val newChatList: List<ChatListUser>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChatList[oldItemPosition].username == newChatList[newItemPosition].username
    }

    override fun getNewListSize(): Int {
        return newChatList.size
    }

    override fun getOldListSize(): Int {
        return oldChatList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChatList[oldItemPosition].id == newChatList[newItemPosition].id
    }
}