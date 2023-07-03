package com.example.sweetfish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetfish.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        binding.changePassword.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.realNameAuthentication.setOnClickListener {
            val intent = Intent(this, RealNameAuthenticationActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
}