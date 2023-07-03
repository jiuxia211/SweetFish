package com.example.sweetfish

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetfish.databinding.ActivityChatBinding
import com.example.sweetfish.ui.chat.ChatAdapter
import com.example.sweetfish.ui.chat.ChatViewModel
import com.example.sweetfish.utils.msg.ChatMsg
import com.example.sweetfish.utils.msg.ChatMsgDiffCallback
import com.example.sweetfish.utils.socketEvent.Message
import org.greenrobot.eventbus.EventBus

class ChatActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val token = intent.getStringExtra("token").toString()
        val uid = intent.getIntExtra("uid", 0)
        binding.back.setOnClickListener {
            finish()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.msgList.layoutManager = layoutManager
        val adapter = ChatAdapter(ArrayList<ChatMsg>())
        binding.msgList.adapter = adapter
        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        chatViewModel.getChatHistory(uid.toString(), token)
        chatViewModel.msgList.observe(this) {
            val result = DiffUtil.calculateDiff(ChatMsgDiffCallback(adapter.msgList, it), true)
            adapter.msgList = it
            result.dispatchUpdatesTo(adapter)
        }
        binding.send.setOnClickListener {
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            val mUid = prefs.getInt("id", -1)
            EventBus.getDefault().post(Message(mUid, binding.inputText.text.toString(), uid))
            chatViewModel.getChatHistory(uid.toString(), token)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}