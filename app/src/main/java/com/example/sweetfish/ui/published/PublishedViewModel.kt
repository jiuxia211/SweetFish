package com.example.sweetfish.ui.published

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublishedViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<Commodity>>()
    val commodityList: LiveData<ArrayList<Commodity>> = _commodityList
    private val _publishedResponseData = MutableLiveData<PublishedJsonData>()
    val purchasedResponseData: LiveData<PublishedJsonData> = _publishedResponseData
    fun initPublishedCommodity(token: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getPublished(token)
            .enqueue(object : Callback<PublishedJsonData> {
                override fun onResponse(
                    call: Call<PublishedJsonData>,
                    response: Response<PublishedJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val commodityList = ArrayList<Commodity>()
                        for (i in responseData.data.posts_lists) {
                            commodityList.add(
                                Commodity(
                                    i.id,
                                    i.title,
                                    i.cover,
                                    i.avatar,
                                    i.username,
                                    i.price
                                )
                            )
                        }
                        _commodityList.postValue(commodityList)
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<PublishedJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}