package com.example.sweetfish

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetfish.databinding.ActivitySoldBinding
import com.example.sweetfish.ui.sold.SoldAdapter
import com.example.sweetfish.ui.sold.SoldViewModel
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class SoldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySoldBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val token = intent.getStringExtra("token").toString()
        //加载已购买的商品信息
        val soldViewModel = ViewModelProvider(this)[SoldViewModel::class.java]
        soldViewModel.initSoldCommodity(token)
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(this, 2)
        binding.commodities.layoutManager = layoutManager
        val adapter = SoldAdapter(ArrayList<Commodity>(), this, token)
        binding.commodities.adapter = adapter
        soldViewModel.soldResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        //观察商品列表是否改变，改变则用DiffUtil刷新recycleview
        soldViewModel.commodityList.observe(this) {
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