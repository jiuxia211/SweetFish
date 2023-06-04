package com.example.sweetfish.retrofitService

import com.example.sweetfish.ui.management.AuditJsonData
import com.example.sweetfish.ui.management.GetAuditJsonData
import retrofit2.Call
import retrofit2.http.*

interface ManagementService {
    @GET("admin/audit")
    fun getAudit(@Header("Authorization") token: String): Call<GetAuditJsonData>

    @FormUrlEncoded
    @POST("admin/audit/set")
    fun audit(
        @Header("Authorization") token: String,
        @Field("postid") pid: String,
        @Field("msg") msg: String,
        @Field("state") state: String
    ): Call<AuditJsonData>
}