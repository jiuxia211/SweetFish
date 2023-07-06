package com.example.sweetfish.utils.user

import androidx.recyclerview.widget.DiffUtil

class SearchUserDiffCallback(
    private val oldChatList: List<SearchUser>,
    private val newChatList: List<SearchUser>
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