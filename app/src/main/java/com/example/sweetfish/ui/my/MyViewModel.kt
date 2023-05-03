package com.example.sweetfish.ui.my

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    private val _userResponseData = MutableLiveData<UserJsonData>().apply {
        value = UserJsonData(0, Data("null", 0, 0, "null", 0, "null"), "null")

    }
    val userResponseData: LiveData<UserJsonData> = _userResponseData
    fun initUserInfo(username: String, token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getUserInfo(username, token).enqueue(object : Callback<UserJsonData> {
            override fun onResponse(call: Call<UserJsonData>, response: Response<UserJsonData>) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _userResponseData.postValue(response.body())
                } else {
                    Log.e("im", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<UserJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}