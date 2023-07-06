package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivitySearchBinding
import com.example.sweetfish.ui.search.SearchTabAdapter
import com.example.sweetfish.ui.search.SearchViewModel
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        binding.vp.adapter = SearchTabAdapter(this)
        binding.vp.isUserInputEnabled = false
        TabLayoutMediator(binding.tb, binding.vp) { tab, position ->
            when (position) {
                0 -> tab.text = "商品"
                1 -> tab.text = "用户"
            }
        }.attach()
        val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.searchImage.setOnClickListener {
            searchViewModel.searchUser(token, binding.editSearchInfo.text.toString(), "1")
            searchViewModel.searchCommodity(token, binding.editSearchInfo.text.toString(), "1")
        }
        binding.searchButton.setOnClickListener {
            searchViewModel.searchUser(token, binding.editSearchInfo.text.toString(), "1")
            searchViewModel.searchCommodity(token, binding.editSearchInfo.text.toString(), "1")
        }
        binding.back.setOnClickListener { finish() }
    }
}