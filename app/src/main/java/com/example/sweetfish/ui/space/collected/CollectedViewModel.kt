package com.example.sweetfish.ui.space.collected

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

class CollectedViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<ArrayList<Commodity>>>()
    val commodityList: LiveData<ArrayList<ArrayList<Commodity>>> = _commodityList
    private val _collectedResponseData = MutableLiveData<CollectedJsonData>()
    val collectedResponseData: LiveData<CollectedJsonData> = _collectedResponseData
    fun initCollectCommodity(token: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getCollected(token)
            .enqueue(object : Callback<CollectedJsonData> {
                override fun onResponse(
                    call: Call<CollectedJsonData>,
                    response: Response<CollectedJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val commodityList = ArrayList<ArrayList<Commodity>>()
                        commodityList.add(ArrayList<Commodity>())
                        for (i in responseData.data.fav_list) {
                            commodityList[0].add(
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
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<CollectedJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}