package com.example.sweetfish

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityTopUpBinding
import com.example.sweetfish.ui.topUp.TopUpViewModel
import com.google.android.material.snackbar.Snackbar

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val topUpViewModel = ViewModelProvider(this)[TopUpViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val mUid = prefs.getInt("id", -1)
        binding.verify.setOnClickListener {
            topUpViewModel.topUp(token, mUid.toString(), binding.editPrice.text.toString())
        }
        topUpViewModel.topUpResponseData.observe(this) {
            Snackbar.make(binding.root, it.message, 3).show()
            if (it.code == 200) {
                finish()
            }
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
}