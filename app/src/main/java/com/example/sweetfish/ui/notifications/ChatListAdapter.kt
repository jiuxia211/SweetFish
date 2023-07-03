package com.example.sweetfish.ui.notifications

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.ChatActivity
import com.example.sweetfish.databinding.ChatListItemsBinding
import com.example.sweetfish.utils.user.ChatListUser

class ChatListAdapter(
    var chatList: List<ChatListUser>,
    val activity: FragmentActivity,
    val token: String
) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ChatListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val avatar: ImageView = binding.avatar
        val username: TextView = binding.username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ChatListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val user = chatList[position]
            val intent = Intent(parent.context, ChatActivity::class.java).apply {
                putExtra("uid", user.id)
                putExtra("token", token)
            }
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = chatList[position]
        Glide.with(activity).load(user.avatar).into(holder.avatar)
        holder.username.text = user.username
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

}