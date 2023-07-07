package com.example.sweetfish.ui.myGiven

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.ui.space.AddChatJsonData
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyGivenViewModel : ViewModel() {
    private val _myGivenList = MutableLiveData<ArrayList<Commodity>>()
    val myGivenList: LiveData<ArrayList<Commodity>> = _myGivenList
    fun getMyGiven(token: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.getMyGiven(token)
            .enqueue(object : Callback<MyGivenJsonData> {
                override fun onResponse(
                    call: Call<MyGivenJsonData>,
                    response: Response<MyGivenJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val commodityList = ArrayList<Commodity>()
                        for (i in responseData.data) {
                            commodityList.add(
                                Commodity(
                                    i.id,
                                    i.title,
                                    i.cover,
                                    i.avatar,
                                    i.username,
                                    i.buy_price
                                )
                            )
                        }
                        _myGivenList.postValue(commodityList)
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<MyGivenJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private val _confirmResponseData = MutableLiveData<ConfirmJsonData>()
    val confirmResponseData: LiveData<ConfirmJsonData> = _confirmResponseData
    fun confirmTransaction(token: String, pid: String, type: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.confirm(token, pid, type)
            .enqueue(object : Callback<ConfirmJsonData> {
                override fun onResponse(
                    call: Call<ConfirmJsonData>,
                    response: Response<ConfirmJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _confirmResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<ConfirmJsonData>, t: Throwable) {
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
}