package com.example.sweetfish

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetfish.databinding.ActivityPublishedBinding
import com.example.sweetfish.ui.published.PublishedAdapter
import com.example.sweetfish.ui.published.PublishedViewModel
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class PublishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPublishedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val publishedViewModel = ViewModelProvider(this)[PublishedViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        //加载已购买的商品信息
        publishedViewModel.initPublishedCommodity(token)
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(this, 2)
        binding.commodities.layoutManager = layoutManager
        var adapter = PublishedAdapter(ArrayList<Commodity>(), this, token)
        binding.commodities.adapter = adapter
        publishedViewModel.purchasedResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        //观察商品列表是否改变，改变则用DiffUtil刷新recycleview
        publishedViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(CommodityDiffCallback(adapter.commodityList, it), true)
            adapter.commodityList = it
            result.dispatchUpdatesTo(adapter)
            if (it.size == 0) {
                binding.nullText.visibility = View.VISIBLE
            } else {
                binding.nullText.visibility = View.GONE
            }
        }
    }
}