package com.example.sweetfish.ui.space.purchased

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.ui.purchased.PurchasedJsonData
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PurchasedViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<Commodity>>()
    val commodityList: LiveData<ArrayList<Commodity>> = _commodityList
    private val _purchasedResponseData = MutableLiveData<PurchasedJsonData>()
    val purchasedResponseData: LiveData<PurchasedJsonData> = _purchasedResponseData
    fun initPurchasedCommodity(token: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getPurchased(token)
            .enqueue(object : Callback<PurchasedJsonData> {
                override fun onResponse(
                    call: Call<PurchasedJsonData>,
                    response: Response<PurchasedJsonData>
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

                override fun onFailure(call: Call<PurchasedJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })

    }

}