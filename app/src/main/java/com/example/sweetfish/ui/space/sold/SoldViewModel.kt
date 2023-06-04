package com.example.sweetfish.ui.space.sold

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.ui.sold.SoldJsonData
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoldViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<Commodity>>()
    val commodityList: LiveData<ArrayList<Commodity>> = _commodityList
    private val _soldResponseData = MutableLiveData<SoldJsonData>()
    val soldResponseData: LiveData<SoldJsonData> = _soldResponseData
    fun initSoldCommodity(token: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getSold(token)
            .enqueue(object : Callback<SoldJsonData> {
                override fun onResponse(
                    call: Call<SoldJsonData>,
                    response: Response<SoldJsonData>
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
                                    i.username
                                )
                            )
                        }
                        _commodityList.postValue(commodityList)
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SoldJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}