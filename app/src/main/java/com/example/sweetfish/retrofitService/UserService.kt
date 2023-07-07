package com.example.sweetfish.retrofitService

import com.example.sweetfish.ui.changePassword.ChangePasswordJsonData
import com.example.sweetfish.ui.changePassword.SendChangePasswordCodeJsonData
import com.example.sweetfish.ui.chat.ChatHistoryJsonData
import com.example.sweetfish.ui.login.LoginJsonData
import com.example.sweetfish.ui.my.UserJsonData
import com.example.sweetfish.ui.myGiven.MyGivenJsonData
import com.example.sweetfish.ui.notifications.ChatListJsonData
import com.example.sweetfish.ui.realNameAuthentication.RealNameAuthenticationJsonData
import com.example.sweetfish.ui.register.RegisterJsonData
import com.example.sweetfish.ui.register.SendCodeJsonData
import com.example.sweetfish.ui.space.AddChatJsonData
import com.example.sweetfish.ui.space.FollowJsonData
import com.example.sweetfish.ui.space.SetAvatarJsonData
import com.example.sweetfish.ui.space.SetBackgroundJsonData
import com.example.sweetfish.ui.topUp.TopUpJsonData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String, @Field("password") password: String
    ): Call<LoginJsonData>

    @FormUrlEncoded
    @POST("user/signup/code")
    fun sendVerificationCode(
        @Field("mail") mail: String
    ): Call<SendCodeJsonData>

    @FormUrlEncoded
    @POST("user/signup")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("mail") mail: String,
        @Field("code") code: String
    ): Call<RegisterJsonData>

    @FormUrlEncoded
    @POST("user/personal_center")
    fun getUserInfo(
        @Field("username") username: String,
        @Header("Authorization") token: String
    ): Call<UserJsonData>

    @Multipart
    @POST("user/set_avatar")
    fun setAvatar(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Call<SetAvatarJsonData>

    @FormUrlEncoded
    @POST("addchat")
    fun addChat(
        @Header("Authorization") token: String,
        @Field("to") uid: String
    ): Call<AddChatJsonData>

    @GET("getchat")
    fun getChat(@Header("Authorization") token: String): Call<ChatListJsonData>

    @FormUrlEncoded
    @POST("chat/history")
    fun getChatHistory(
        @Header("Authorization") token: String,
        @Field("user") uid: String
    ): Call<ChatHistoryJsonData>

    @FormUrlEncoded
    @POST("user/change_password")
    fun changePassword(
        @Header("Authorization") token: String, @Field("password") password: String,
        @Field("code") code: String
    ): Call<ChangePasswordJsonData>

    @FormUrlEncoded
    @POST("user/change_password/code")
    fun sendChangePasswordCode(
        @Header("Authorization") token: String,
        @Field("mail") mail: String
    ): Call<SendChangePasswordCodeJsonData>

    @FormUrlEncoded
    @POST("user/real_name")
    fun realNameAuthentication(
        @Header("Authorization") token: String,
        @Field("id") id: String,
        @Field("name") name: String
    ): Call<RealNameAuthenticationJsonData>

    @FormUrlEncoded
    @POST("user/follow")
    fun follow(
        @Header("Authorization") token: String,
        @Field("aim_id") uid: String,
        @Field("type") type: String
    ): Call<FollowJsonData>

    @Multipart
    @POST("background")
    fun setBackground(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Call<SetBackgroundJsonData>

    @FormUrlEncoded
    @POST("admin/give_money")
    fun topUp(
        @Header("Authorization") token: String,
        @Field("to") uid: String,
        @Field("money") money: String
    ): Call<TopUpJsonData>
    
    @GET("my_given")
    fun getMyGiven(@Header("Authorization") token: String): Call<MyGivenJsonData>
}