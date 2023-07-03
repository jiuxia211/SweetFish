package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetfish.databinding.ActivitySearchBinding
import com.example.sweetfish.ui.search.SearchTabAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        binding.vp.adapter = SearchTabAdapter(this)
        TabLayoutMediator(binding.tb, binding.vp) { tab, position ->
            when (position) {
                0 -> tab.text = "购买"
                1 -> tab.text = "收藏"
            }
        }.attach()
    }
}