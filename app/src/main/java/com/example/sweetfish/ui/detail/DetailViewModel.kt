package com.example.sweetfish.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.ui.my.UserJsonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailJsonData = MutableLiveData<DetailJsonData>()
    val detailJsonData: LiveData<DetailJsonData> = _detailJsonData
    fun getDetail(token: String, pid: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getDetail(token, pid).enqueue(object : Callback<DetailJsonData> {
            override fun onResponse(
                call: Call<DetailJsonData>,
                response: Response<DetailJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _detailJsonData.postValue(response.body())
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<DetailJsonData>, t: Throwable) {
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
                    Log.d("zz", response.body().toString())
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
}