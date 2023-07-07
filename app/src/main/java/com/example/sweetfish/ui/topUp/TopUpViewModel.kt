package com.example.sweetfish.ui.topUp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopUpViewModel : ViewModel() {
    private val _topUpResponseData = MutableLiveData<TopUpJsonData>()
    val topUpResponseData:
            LiveData<TopUpJsonData> = _topUpResponseData

    fun topUp(token: String, uid: String, price: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.topUp(token, uid, price)
            .enqueue(object : Callback<TopUpJsonData> {
                override fun onResponse(
                    call: Call<TopUpJsonData>,
                    response: Response<TopUpJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _topUpResponseData.postValue(response.body())
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<TopUpJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }


}