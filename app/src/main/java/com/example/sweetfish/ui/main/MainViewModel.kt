package com.example.sweetfish.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.ui.my.UserJsonData
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _userResponseData = MutableLiveData<UserJsonData>()
    val userResponseData: LiveData<UserJsonData> = _userResponseData
    private val _commodityList = MutableLiveData<ArrayList<Commodity>>()
    val commodityList: LiveData<ArrayList<Commodity>> = _commodityList
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

    fun initRecommendInfo(token: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getRecommend(token).enqueue(object : Callback<RecommendJsonData> {
            override fun onResponse(
                call: Call<RecommendJsonData>,
                response: Response<RecommendJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    val commodityList = ArrayList<Commodity>()
                    for (i in responseData.data) {
                        commodityList.add(
                            Commodity(
                                i.post_id,
                                i.title,
                                i.cover,
                                i.avatar,
                                i.username
                            )
                        )

                    }
                    _commodityList.postValue(commodityList)
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<RecommendJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}