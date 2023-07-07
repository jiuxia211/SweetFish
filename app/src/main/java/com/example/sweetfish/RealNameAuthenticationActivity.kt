package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityRealNameAuthenticationBinding
import com.example.sweetfish.ui.realNameAuthentication.RealNameAuthenticationViewModel
import com.google.android.material.snackbar.Snackbar

class RealNameAuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityRealNameAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val realNameAuthenticationViewModel =
            ViewModelProvider(this)[RealNameAuthenticationViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        binding.verify.setOnClickListener {
            realNameAuthenticationViewModel.realNameAuthentication(
                token,
                binding.identityNum.text.toString(),
                binding.name.text.toString()
            )
        }
        realNameAuthenticationViewModel.realNameAuthenticationResponseData.observe(this) {
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