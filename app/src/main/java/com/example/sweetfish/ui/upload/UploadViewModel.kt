package com.example.sweetfish.ui.upload

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ServiceCreator
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadViewModel : ViewModel() {
    private val _uploadResponseData = MutableLiveData<UploadJsonData>()

    val uploadResponseData: LiveData<UploadJsonData> = _uploadResponseData

    fun upload(
        token: String,
        title: RequestBody,
        content: RequestBody,
        price: RequestBody,
        account: RequestBody,
        password: RequestBody,
        parts: ArrayList<MultipartBody.Part>
    ) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.upload(token, title, content, price, account, password, parts)
            .enqueue(object : Callback<UploadJsonData> {
                override fun onResponse(
                    call: Call<UploadJsonData>,
                    response: Response<UploadJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _uploadResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<UploadJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}