package com.example.sweetfish.ui.space

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpaceViewModel : ViewModel() {
    private val _setAvatarResponseData = MutableLiveData<SetAvatarJsonData>()
    val setAvatarResponseData: LiveData<SetAvatarJsonData> = _setAvatarResponseData
    fun setAvatar(token: String, avatar: MultipartBody.Part) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.setAvatar(token, avatar)
            .enqueue(object : Callback<SetAvatarJsonData> {
                override fun onResponse(
                    call: Call<SetAvatarJsonData>,
                    response: Response<SetAvatarJsonData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        Log.d("zz", response.body().toString())
                        _setAvatarResponseData.postValue(response.body())
                    } else {
                        Log.e("im", "返回了空的json数据")
                    }
                }

                override fun onFailure(call: Call<SetAvatarJsonData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}