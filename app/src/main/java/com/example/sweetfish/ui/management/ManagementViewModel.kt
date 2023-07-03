package com.example.sweetfish.ui.management

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.CommodityService
import com.example.sweetfish.retrofitService.ManagementService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.ui.detail.DetailJsonData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagementViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<String>>()
    val commodityList: LiveData<ArrayList<String>> = _commodityList
    private val _auditResponseData = MutableLiveData<AuditJsonData>()
    val auditResponseData: LiveData<AuditJsonData> = _auditResponseData
    private val _getAuditResponseData = MutableLiveData<GetAuditJsonData>()
    val getAuditResponseData: LiveData<GetAuditJsonData> = _getAuditResponseData
    private val _detailJsonData = MutableLiveData<DetailJsonData>()
    val detailJsonData: LiveData<DetailJsonData> = _detailJsonData
    fun getDetail(token: String, pid: String) {
        val commodityService = ServiceCreator.create(CommodityService::class.java)
        commodityService.getDetail(token, pid).enqueue(object : Callback<DetailJsonData> {
            override fun onResponse(
                call: Call<DetailJsonData>,
                response: Response<DetailJsonData>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _detailJsonData.postValue(response.body())
                } else {
                    Log.e("zz", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<DetailJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getAuditCommodity(token: String) {
        val managementService = ServiceCreator.create(ManagementService::class.java)
        managementService.getAudit(token)
            .enqueue(object : Callback<GetAuditJsonData> {
                override fun onResponse(
                    call: Call<GetAuditJsonData>,
                    response: Response<GetAuditJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        val commodityList = ArrayList<String>()
                        for (i in responseData.data.post_list) {
                            commodityList.add(
                                i.id.toString()
                            )
                        }
                        _commodityList.postValue(commodityList)
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<GetAuditJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun Audit(token: String, pid: String, msg: String, state: String) {
        val managementService = ServiceCreator.create(ManagementService::class.java)
        managementService.audit(token, pid, msg, state)
            .enqueue(object : Callback<AuditJsonData> {
                override fun onResponse(
                    call: Call<AuditJsonData>,
                    response: Response<AuditJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())

                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<AuditJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}