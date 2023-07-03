package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivityPhotoBinding

class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("url")
        Glide.with(this).load(url).into(binding.photoView)
        binding.back.setOnClickListener {
            finish()
        }
    }
}