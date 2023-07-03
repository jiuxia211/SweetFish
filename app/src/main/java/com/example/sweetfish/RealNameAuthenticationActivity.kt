package com.example.sweetfish

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityRealNameAuthenticationBinding
import com.example.sweetfish.ui.realNameAuthentication.RealNameAuthenticationViewModel

class RealNameAuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRealNameAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val realNameAuthenticationViewModel =
            ViewModelProvider(this)[RealNameAuthenticationViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        binding.identityNum.setOnClickListener {
            realNameAuthenticationViewModel.realNameAuthentication(
                token,
                binding.identityNum.text.toString(),
                binding.name.text.toString()
            )
        }
        realNameAuthenticationViewModel.realNameAuthenticationResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                finish()
            }
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
}