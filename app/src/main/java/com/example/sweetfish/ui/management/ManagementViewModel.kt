package com.example.sweetfish.ui.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ManagementService
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.utils.commodity.Commodity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagementViewModel : ViewModel() {
    private val _commodityList = MutableLiveData<ArrayList<ArrayList<Commodity>>>()
    val commodityList: LiveData<ArrayList<ArrayList<Commodity>>> = _commodityList
    private val _auditResponseData = MutableLiveData<AuditJsonData>()
    val auditResponseData: LiveData<AuditJsonData> = _auditResponseData
    private val _getAuditResponseData = MutableLiveData<GetAuditJsonData>()
    val getAuditResponseData: LiveData<GetAuditJsonData> = _getAuditResponseData
    fun getAuditCommodity(token: String) {
        val managementService = ServiceCreator.create(ManagementService::class.java)
        managementService.getAudit(token)
            .enqueue(object : Callback<GetAuditJsonData> {
                override fun onResponse(
                    call: Call<GetAuditJsonData>,
                    response: Response<GetAuditJsonData>
                ) {
                    val responseData = response.body()
//                    if (responseData != null) {
//                        Log.d("zz", response.body().toString())
//                        val commodityList = ArrayList<ArrayList<Commodity>>()
//                        commodityList.add(ArrayList<Commodity>())
//                        for (i in responseData.data.post_list) {
//                            commodityList[0].add(
//                                Commodity(
//                                    i.post_id,
//                                    i.title,
//                                    i.cover,
//                                    i.avatar,
//                                    i.username
//                                )
//                            )
//                        }
//                        _commodityList.postValue(commodityList)
//                    } else {
//                        Log.e("im", "返回了空的json数据")
//                    }
                }

                override fun onFailure(call: Call<GetAuditJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}