package com.example.sweetfish.ui.space

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.ui.my.UserJsonData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpaceViewModel : ViewModel() {
    private val _setAvatarResponseData = MutableLiveData<SetAvatarJsonData>()
    val setAvatarResponseData: LiveData<SetAvatarJsonData> = _setAvatarResponseData

    fun setAvatar(token: String, avatar: MultipartBody.Part) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.setAvatar(token, avatar)
            .enqueue(object : Callback<SetAvatarJsonData> {
                override fun onResponse(
                    call: Call<SetAvatarJsonData>,
                    response: Response<SetAvatarJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _setAvatarResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SetAvatarJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private val _userResponseData = MutableLiveData<UserJsonData>()
    val userResponseData: LiveData<UserJsonData> = _userResponseData
    fun initUserInfo(username: String, token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getUserInfo(username, token).enqueue(object : Callback<UserJsonData> {
            override fun onResponse(call: Call<UserJsonData>, response: Response<UserJsonData>) {
                val responseData = response.body()
                if (responseData != null) {
                    _userResponseData.postValue(response.body())
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<UserJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private val _addChatResponseData = MutableLiveData<AddChatJsonData>()
    val addChatResponseData: LiveData<AddChatJsonData> = _addChatResponseData
    fun addChat(to: String, token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.addChat(token, to).enqueue(object : Callback<AddChatJsonData> {
            override fun onResponse(
                call: Call<AddChatJsonData>,
                response: Response<AddChatJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _addChatResponseData.postValue(response.body())
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<AddChatJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private val _followResponseData = MutableLiveData<FollowJsonData>()
    val followResponseData: LiveData<FollowJsonData> = _followResponseData
    fun follow(uid: String, type: String, token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.follow(token, uid, type).enqueue(object : Callback<FollowJsonData> {
            override fun onResponse(
                call: Call<FollowJsonData>,
                response: Response<FollowJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _followResponseData.postValue(response.body())
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<FollowJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private val _setBackgroundResponseData = MutableLiveData<SetBackgroundJsonData>()
    val setBackgroundResponseData: LiveData<SetBackgroundJsonData> = _setBackgroundResponseData
    fun setBackground(token: String, background: MultipartBody.Part) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.setBackground(token, background)
            .enqueue(object : Callback<SetBackgroundJsonData> {
                override fun onResponse(
                    call: Call<SetBackgroundJsonData>,
                    response: Response<SetBackgroundJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _setBackgroundResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SetBackgroundJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}