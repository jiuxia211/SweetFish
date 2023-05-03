package com.example.sweetfish.ui.purchased

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PurchasedViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<Commodity>>().apply {
        val list = ArrayList<Commodity>()
        value = list
    }
    val commodityList: LiveData<ArrayList<Commodity>> = _commodityList
    private val _purchasedResponseData = MutableLiveData<PurchasedJsonData>().apply {
        value = PurchasedJsonData(0, Data(posts_lists = ArrayList<PostsLists>()), "ZZ")
    }
    val purchasedResponseData: LiveData<PurchasedJsonData> = _purchasedResponseData
    fun initPurchasedCommodity(token: String) {

    }
}