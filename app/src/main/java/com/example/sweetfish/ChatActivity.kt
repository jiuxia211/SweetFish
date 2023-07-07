package com.example.sweetfish

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetfish.databinding.ActivityChatBinding
import com.example.sweetfish.databinding.MsgMenuBinding
import com.example.sweetfish.ui.chat.ChatAdapter
import com.example.sweetfish.ui.chat.ChatViewModel
import com.example.sweetfish.utils.msg.ChatMsg
import com.example.sweetfish.utils.msg.ChatMsg.Companion.TYPE_RECEIVED
import com.example.sweetfish.utils.msg.ChatMsgDiffCallback
import com.example.sweetfish.utils.socketEvent.Message
import com.example.sweetfish.utils.socketEvent.MessageReceipt
import com.example.sweetfish.utils.socketEvent.Revocation
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class ChatActivity : AppCompatActivity(), ChatAdapter.OnItemClickListener {
    private lateinit var popupWindow: PopupWindow
    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    var token: String = ""
    var uid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        token = intent.getStringExtra("token").toString()
        uid = intent.getIntExtra("uid", 0)
        val username = intent.getStringExtra("username").toString()
        binding.username.text = username
        binding.back.setOnClickListener {
            finish()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.msgList.layoutManager = layoutManager
        val adapter = ChatAdapter(ArrayList<ChatMsg>(), this)
        binding.msgList.adapter = adapter
        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        chatViewModel.getChatHistory(uid.toString(), token)
        chatViewModel.msgList.observe(this) {
            val result = DiffUtil.calculateDiff(ChatMsgDiffCallback(adapter.msgList, it), true)
            adapter.msgList = it
            result.dispatchUpdatesTo(adapter)
            binding.msgList.scrollToPosition(it.size - 1)
        }
        binding.send.setOnClickListener {
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            val mUid = prefs.getInt("id", -1)
            EventBus.getDefault().post(Message(mUid, binding.inputText.text.toString(), uid))
            binding.inputText.setText("")
            
            chatViewModel.getChatHistory(uid.toString(), token)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onShowMenuClick(view: View, position: Int, msg: ChatMsg) {
        popupWindow = PopupWindow(view)
        val menuLayout = MsgMenuBinding.inflate(layoutInflater)
        popupWindow.contentView = menuLayout.root
        popupWindow.width = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.animationStyle = R.style.PopupAnimation
        popupWindow.isOutsideTouchable = true
        if (msg.type == TYPE_RECEIVED) {
            popupWindow.showAsDropDown(view)
        } else {
            val xOffset = -(popupWindow.contentView.measuredWidth - view.width)
            popupWindow.showAsDropDown(view, xOffset, 0)
        }
        menuLayout.revocation.setOnClickListener {
            if (msg.type == ChatMsg.TYPE_SENT) {
                EventBus.getDefault().post(Revocation(msg.id))
                Log.d("zz", "点击撤回")
            } else {
                Toast.makeText(this, "不能撤回别人的消息哦", Toast.LENGTH_SHORT).show()
            }
            popupWindow.dismiss()
        }
    }

    @Subscribe
    fun onMessageReceipt(event: MessageReceipt) {
        if (event.type == "receive" || event.type == "receipt" || event.type == "revocation") {
            val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
            chatViewModel.getChatHistory(uid.toString(), token)
        }
    }
}