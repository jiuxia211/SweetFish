package com.example.sweetfish.retrofitService

import com.example.sweetfish.ui.login.LoginJsonData
import com.example.sweetfish.ui.my.UserJsonData
import com.example.sweetfish.ui.register.RegisterJsonData
import com.example.sweetfish.ui.register.SendCodeJsonData
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
}