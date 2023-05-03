package com.example.sweetfish.retrofitService

import com.example.sweetfish.ui.upload.UploadJsonData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface CommodityService {
    @Multipart
    @POST("createposts")
    fun upload(
        @Header("Authorization") token: String,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody,
        @Part("price") price: RequestBody,
        @Part files: List<MultipartBody.Part>,
    ): Call<UploadJsonData>
}