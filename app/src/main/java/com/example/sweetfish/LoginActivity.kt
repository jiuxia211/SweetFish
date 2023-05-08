package com.example.sweetfish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityLoginBinding
import com.example.sweetfish.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.loginResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("token", it.data.token)
                intent.putExtra("username", it.data.username)
                startActivity(intent)
            }
        }
        loginViewModel.username.observe(this) {
            binding.editAccount.setText(it)
        }
        loginViewModel.password.observe(this) {
            binding.editPassword.setText(it)
        }
        //若勾选记住密码，加载存储的账号密码
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRePassword = loginViewModel.initInfo(prefs)
        if (isRePassword) {
            binding.rememberPassword.isChecked = true
        }
        //登录操作
        binding.login.setOnClickListener {
            val editor = getPreferences(Context.MODE_PRIVATE).edit()
            if (binding.rememberPassword.isChecked) {
                loginViewModel.saveInfo(
                    editor,
                    binding.editAccount.text.toString(),
                    binding.editPassword.text.toString()
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