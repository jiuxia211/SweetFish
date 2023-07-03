package com.example.sweetfish.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetfish.R
import com.example.sweetfish.utils.msg.ChatMsg

class ChatAdapter(var msgList: List<ChatMsg>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }

    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType ==
        ChatMsg.TYPE_RECEIVED
    ) {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.msg_left,
            parent, false
        )
        LeftViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.msg_right,
            parent, false
        )
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = msgList.size

}