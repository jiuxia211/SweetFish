package com.example.sweetfish.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.utils.user.ChatListUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {
    private val _chatList = MutableLiveData<ArrayList<ChatListUser>>()
    val chatList: LiveData<ArrayList<ChatListUser>> = _chatList
    private val _chatListResponseData = MutableLiveData<ChatListJsonData>()
    val chatListResponseData: LiveData<ChatListJsonData> = _chatListResponseData
    fun getChatList(token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getChat(token).enqueue(object : Callback<ChatListJsonData> {
            override fun onResponse(
                call: Call<ChatListJsonData>,
                response: Response<ChatListJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    val chatList = ArrayList<ChatListUser>()
                    for (i in responseData.data) {
                        chatList.add(ChatListUser(i.avatar, i.username, i.id))
                    }
                    _chatList.postValue(chatList)
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<ChatListJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}