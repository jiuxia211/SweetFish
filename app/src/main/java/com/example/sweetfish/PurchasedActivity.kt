package com.example.sweetfish

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetfish.databinding.ActivityPurchasedBinding
import com.example.sweetfish.ui.purchased.PurchasedAdapter
import com.example.sweetfish.ui.purchased.PurchasedViewModel
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class PurchasedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityPurchasedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val purchasedViewModel = ViewModelProvider(this)[PurchasedViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        //加载已购买的商品信息
        purchasedViewModel.initPurchasedCommodity(token)
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(this, 2)
        binding.commodities.layoutManager = layoutManager
        var adapter = PurchasedAdapter(ArrayList<Commodity>(), this)
        binding.commodities.adapter = adapter
        purchasedViewModel.purchasedResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        //观察商品列表是否改变，改变则用DiffUtil刷新recycleview
        purchasedViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(CommodityDiffCallback(adapter.commodityList, it), true)
            adapter.commodityList = it
            result.dispatchUpdatesTo(adapter)
        }
        //下拉刷新
        binding.swipeRefresh.setColorSchemeResources(R.color.teal_200)
        binding.swipeRefresh.setOnRefreshListener {
            purchasedViewModel.initPurchasedCommodity(token)
            binding.swipeRefresh.isRefreshing = false
        }


    }
}