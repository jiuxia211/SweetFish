package com.example.sweetfish.ui.realNameAuthentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RealNameAuthenticationViewModel : ViewModel() {
    private val _realNameAuthenticationResponseData =
        MutableLiveData<RealNameAuthenticationJsonData>()
    val realNameAuthenticationResponseData: LiveData<RealNameAuthenticationJsonData> =
        _realNameAuthenticationResponseData

    fun realNameAuthentication(token: String, id: String, name: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.realNameAuthentication(token, id, name)
            .enqueue(object : Callback<RealNameAuthenticationJsonData> {
                override fun onResponse(
                    call: Call<RealNameAuthenticationJsonData>,
                    response: Response<RealNameAuthenticationJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _realNameAuthenticationResponseData.postValue(response.body())
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<RealNameAuthenticationJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}