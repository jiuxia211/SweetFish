package com.example.sweetfish

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityRegisterBinding
import com.example.sweetfish.ui.register.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        registerViewModel.registerResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                finish()
            }
        }
        registerViewModel.sendCodeResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        binding.sendCode.setOnClickListener {
            if (binding.editMail.text.isEmpty()) {
                Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show()
            } else {
                registerViewModel.sendCode(binding.editMail.text.toString())
            }
        }
        binding.register.setOnClickListener {
            if (binding.editCode.text.isEmpty()) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show()
            } else {
                registerViewModel.register(
                    binding.editUsername.text.toString(), binding.editPassword.text.toString(),
                    binding.editMail.text.toString(), binding.editCode.text.toString()
                )
            }


        }
    }
}