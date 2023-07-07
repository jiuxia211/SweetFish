package com.example.sweetfish.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import com.example.sweetfish.ui.space.FollowJsonData
import com.example.sweetfish.utils.commodity.SearchCommodity
import com.example.sweetfish.utils.user.SearchUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel() : ViewModel() {
    private val _userList = MutableLiveData<ArrayList<SearchUser>>()
    val userList: LiveData<ArrayList<SearchUser>> = _userList
    fun searchUser(token: String, key: String, page: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.search(token, key, "user", page)
            .enqueue(object : Callback<SearchJsonData> {
                override fun onResponse(
                    call: Call<SearchJsonData>,
                    response: Response<SearchJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val userList = ArrayList<SearchUser>()
                        for (i in responseData.data) {
                            userList.add(
                                SearchUser(
                                    i.avatar,
                                    i.username,
                                    i.turnover,
                                    i.followed,
                                    i.id
                                )
                            )
                        }
                        _userList.postValue(userList)
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SearchJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private val _commodityList = MutableLiveData<ArrayList<SearchCommodity>>()
    val commodityList: LiveData<ArrayList<SearchCommodity>> = _commodityList
    fun searchCommodity(token: String, key: String, page: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.search(token, key, "post", page)
            .enqueue(object : Callback<SearchJsonData> {
                override fun onResponse(
                    call: Call<SearchJsonData>,
                    response: Response<SearchJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val commodityList = ArrayList<SearchCommodity>()
                        for (i in responseData.data) {
                            commodityList.add(
                                SearchCommodity(
                                    i.avatar,
                                    i.cover,
                                    i.price,
                                    i.post_id,
                                    i.title,
                                    i.user_id,
                                    i.username
                                )
                            )
                        }
                        _commodityList.postValue(commodityList)
                    } else {
                        Log.e("zz", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SearchJsonData>, t: Throwable) {
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
}