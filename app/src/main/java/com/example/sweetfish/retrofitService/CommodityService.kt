package com.example.sweetfish.retrofitService

import com.example.sweetfish.ui.collect.CollectedJsonData
import com.example.sweetfish.ui.detail.CollectJsonData
import com.example.sweetfish.ui.detail.DetailJsonData
import com.example.sweetfish.ui.main.RecommendJsonData
import com.example.sweetfish.ui.published.PublishedJsonData
import com.example.sweetfish.ui.purchased.PurchasedJsonData
import com.example.sweetfish.ui.search.SearchJsonData
import com.example.sweetfish.ui.sold.SoldJsonData
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
        @Part("account") account: RequestBody,
        @Part("psw") psw: RequestBody,
        @Part files: List<MultipartBody.Part>,
    ): Call<UploadJsonData>

    @GET("buyer/bought")
    fun getPurchased(@Header("Authorization") token: String): Call<PurchasedJsonData>

    @GET("buyer/favposts")
    fun getCollected(@Header("Authorization") token: String): Call<CollectedJsonData>

    @GET("seller/posted_posts")
    fun getPublished(@Header("Authorization") token: String): Call<PublishedJsonData>

    @GET("seller/sold_posts")
    fun getSold(@Header("Authorization") token: String): Call<SoldJsonData>

    @FormUrlEncoded
    @POST("detail")
    fun getDetail(
        @Header("Authorization") token: String,
        @Field("post_id") pid: String
    ): Call<DetailJsonData>

    @GET("buyer/posts")
    fun getRecommend(@Header("Authorization") token: String): Call<RecommendJsonData>

    @FormUrlEncoded
    @POST("buyer/fav")
    fun collect(
        @Header("Authorization") token: String,
        @Field("post_id") pid: String
    ): Call<CollectJsonData>

    @FormUrlEncoded
    @POST("search")
    fun search(
        @Header("Authorization") token: String,
        @Field("key") key: String,
        @Field("type") type: String,
        @Field("page") page: String
    ): Call<SearchJsonData>
}