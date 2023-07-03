package com.example.sweetfish.ui.changePassword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {
    private val _changePasswordResponseData = MutableLiveData<ChangePasswordJsonData>()
    val changePasswordResponseData:
            LiveData<ChangePasswordJsonData> = _changePasswordResponseData
    private val _sendChangePasswordCodeResponseData =
        MutableLiveData<SendChangePasswordCodeJsonData>()
    val sendChangePasswordCodeResponseData: LiveData<SendChangePasswordCodeJsonData> =
        _sendChangePasswordCodeResponseData

    fun sendChangePasswordCode(token: String, mail: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.sendChangePasswordCode(token, mail)
            .enqueue(object : Callback<SendChangePasswordCodeJsonData> {
                override fun onResponse(
                    call: Call<SendChangePasswordCodeJsonData>,
                    response: Response<SendChangePasswordCodeJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _sendChangePasswordCodeResponseData.postValue(response.body())
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SendChangePasswordCodeJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun changePassword(token: String, code: String, newPassword: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.changePassword(token, newPassword, code)
            .enqueue(object : Callback<ChangePasswordJsonData> {
                override fun onResponse(
                    call: Call<ChangePasswordJsonData>,
                    response: Response<ChangePasswordJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _changePasswordResponseData.postValue(response.body())
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<ChangePasswordJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}


