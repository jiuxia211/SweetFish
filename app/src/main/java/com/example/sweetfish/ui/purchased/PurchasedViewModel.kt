package com.example.sweetfish.ui.purchased

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
//        val commodityList = ArrayList<Commodity>()
//        commodityList.apply {
//            add(Commodity(1, "生气", R.drawable.angry, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(2, "困困", R.drawable.sleepy, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(3, "哭哭", R.drawable.cry, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(4, "抱抱", R.drawable.hug, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(5, "期待", R.drawable.expect, R.drawable.default_avatar, "jiuxia211"))
//            add(
//                Commodity(
//                    6,
//                    "吃瓜",
//                    R.drawable.eatwatermelon,
//                    R.drawable.default_avatar,
//                    "jiuxia211"
//                )
//            )
//            add(Commodity(7, "点赞", R.drawable.like, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(8, "捏脸", R.drawable.pinchface, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(9, "乖巧", R.drawable.lovely, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(10, "宁静", R.drawable.quiet, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(11, "沉思", R.drawable.ruminate, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(12, "大佬喝茶", R.drawable.tea, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(13, "不高兴", R.drawable.unhappy, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(14, "惊讶", R.drawable.surprise, R.drawable.default_avatar, "jiuxia211"))
//            add(Commodity(15, "空洞", R.drawable.voidx, R.drawable.default_avatar, "jiuxia211"))
//            _commodityList.value = commodityList
//        }
    }
}