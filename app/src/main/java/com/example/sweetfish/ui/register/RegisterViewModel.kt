package com.example.sweetfish.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _registerResponseData = MutableLiveData<RegisterJsonData>().apply {
        value = RegisterJsonData(0, Data(0, "username"), "开始注册吧！")
    }
    val registerResponseData: LiveData<RegisterJsonData> = _registerResponseData
    private val _sendCodeResponseData = MutableLiveData<SendCodeJsonData>().apply {
        value = SendCodeJsonData(0, "欢迎来到甜鱼")
    }
    val sendCodeResponseData: LiveData<SendCodeJsonData> = _sendCodeResponseData

    fun sendCode(mail: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.sendVerificationCode(mail)
            .enqueue(object : Callback<SendCodeJsonData> {
                override fun onResponse(
                    call: Call<SendCodeJsonData>,
                    response: Response<SendCodeJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _sendCodeResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SendCodeJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })

    }

    fun register(username: String, password: String, mail: String, code: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.register(username, password, mail, code)
            .enqueue(object : Callback<RegisterJsonData> {
                override fun onResponse(
                    call: Call<RegisterJsonData>,
                    response: Response<RegisterJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _registerResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<RegisterJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })

    }
}