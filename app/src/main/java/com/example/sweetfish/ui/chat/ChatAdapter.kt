package com.example.sweetfish.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetfish.databinding.MsgLeftBinding
import com.example.sweetfish.databinding.MsgRightBinding
import com.example.sweetfish.utils.msg.ChatMsg

class ChatAdapter(var msgList: List<ChatMsg>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onShowMenuClick(view: View, position: Int, msg: ChatMsg)
    }

    inner class LeftViewHolder(binding: MsgLeftBinding) : RecyclerView.ViewHolder(binding.root) {
        val leftMsg: TextView = binding.leftMsg
    }

    inner class RightViewHolder(binding: MsgRightBinding) : RecyclerView.ViewHolder(binding.root) {
        val rightMsg: TextView = binding.rightMsg
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType ==
        ChatMsg.TYPE_RECEIVED
    ) {
        val binding = MsgLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        LeftViewHolder(binding)
    } else {
        val binding = MsgRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        RightViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> {
                holder.leftMsg.text = msg.content
                holder.itemView.setOnLongClickListener {
                    listener.onShowMenuClick(holder.itemView, position, msg)
                    true
                }
            }
            is RightViewHolder -> {
                holder.rightMsg.text = msg.content
                holder.itemView.setOnLongClickListener {
                    listener.onShowMenuClick(holder.itemView, position, msg)
                    true
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = msgList.size

}