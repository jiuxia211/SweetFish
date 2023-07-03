package com.example.sweetfish.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.utils.msg.ChatMsg
import com.example.sweetfish.utils.msg.ChatMsg.Companion.TYPE_RECEIVED
import com.example.sweetfish.utils.msg.ChatMsg.Companion.TYPE_SENT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel : ViewModel() {
    private val _msgList = MutableLiveData<ArrayList<ChatMsg>>()
    val msgList: LiveData<ArrayList<ChatMsg>> = _msgList
    fun getChatHistory(uid: String, token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getChatHistory(token, uid)
            .enqueue(object : Callback<ChatHistoryJsonData> {
                override fun onResponse(
                    call: Call<ChatHistoryJsonData>,
                    response: Response<ChatHistoryJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val msgList = ArrayList<ChatMsg>()
                        for (i in responseData.data) {
                            if (i.from == uid.toInt()) {
                                msgList.add(ChatMsg(i.message, TYPE_RECEIVED))
                            } else {
                                msgList.add(ChatMsg(i.message, TYPE_SENT))
                            }

                        }
                        _msgList.postValue(msgList)
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<ChatHistoryJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}