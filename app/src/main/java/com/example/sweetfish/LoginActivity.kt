package com.example.sweetfish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityLoginBinding
import com.example.sweetfish.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.loginResponseData.observe(this) {
            if (it.code == 200) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("token", it.data.token)
                intent.putExtra("username", it.data.username)
                intent.putExtra("uid", it.data.id)
                startActivity(intent)
            }
        }
        loginViewModel.userInfo.observe(this) {
            if (it.isRePassword) {
                binding.rememberPassword.isChecked = true
                binding.editAccount.setText(it.account)
                binding.editPassword.setText(it.password)
            }
            if (it.isAutoLogin) {
                val editor = getPreferences(Context.MODE_PRIVATE).edit()
                if (binding.rememberPassword.isChecked) {
                    binding.autoLogin.isChecked = true
                    loginViewModel.saveInfo(
                        editor,
                        binding.editAccount.text.toString(),
                        binding.editPassword.text.toString(),
                        binding.rememberPassword.isChecked,
                        binding.autoLogin.isChecked
                    )
                } else {
                    binding.autoLogin.isChecked = false
                    editor.clear()
                }
                loginViewModel.login(
                    binding.editAccount.text.toString(),
                    binding.editPassword.text.toString()
                )
            }
        }
        //加载用户信息,用于记住密码和自动登录
        val prefs = getPreferences(Context.MODE_PRIVATE)
        loginViewModel.initUserInfo(prefs)
        //登录操作
        binding.login.setOnClickListener {
            val editor = getPreferences(Context.MODE_PRIVATE).edit()
            if (binding.rememberPassword.isChecked) {
                loginViewModel.saveInfo(
                    editor,
                    binding.editAccount.text.toString(),
                    binding.editPassword.text.toString(),
                    binding.rememberPassword.isChecked,
                    binding.autoLogin.isChecked
                )
            } else {
                editor.clear()
            }
            loginViewModel.login(
                binding.editAccount.text.toString(),
                binding.editPassword.text.toString()
            )
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }
        //进入注册界面
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


}