package com.example.sweetfish.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetfish.retrofitService.ServiceCreator
import com.example.sweetfish.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> = _userInfo
    private val _loginResponseData = MutableLiveData<LoginJsonData>().apply {
        value = LoginJsonData(0, Data(0, 0, "token", "username"), "欢迎来到甜鱼")
    }
    val loginResponseData: LiveData<LoginJsonData> = _loginResponseData
    fun login(username: String, password: String) {
        val userService = ServiceCreator.create(UserService::class.java)
        userService.login(username, password).enqueue(object : Callback<LoginJsonData> {
            override fun onResponse(call: Call<LoginJsonData>, response: Response<LoginJsonData>) {
                val responseData = response.body()
                if (responseData != null) {
                    Log.d("zz", response.body().toString())
                    _loginResponseData.postValue(response.body())
                } else {
                    Log.e("im", "返回了空的json数据")
                }
            }

            override fun onFailure(call: Call<LoginJsonData>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    fun saveInfo(
        editor: SharedPreferences.Editor,
        account: String,
        password: String,
        checked: Boolean,
        checked1: Boolean
    ) {
        editor.putBoolean("remember_password", checked)
        editor.putBoolean("auto_login", checked1)
        editor.putString("account", account)
        editor.putString("password", password)
        editor.apply()
    }

    fun initUserInfo(prefs: SharedPreferences) {
        val account = prefs.getString("account", "").toString()
        val password = prefs.getString("password", "").toString()
        val isRePassword = prefs.getBoolean("remember_password", false)
        val isAutoLogin = prefs.getBoolean("auto_login", false)
        _userInfo.value = UserInfo(account, password, isRePassword, isAutoLogin)

    }


}