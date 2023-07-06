package com.example.sweetfish

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityCollectBinding
import com.example.sweetfish.ui.collect.CollectedAdapter
import com.example.sweetfish.ui.collect.CollectedFirstList
import com.example.sweetfish.ui.collect.CollectedViewModel
import com.example.sweetfish.utils.commodity.Commodity

class CollectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityCollectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val collectedViewModel = ViewModelProvider(this)[CollectedViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        //加载已收藏的商品信息
        collectedViewModel.initCollectCommodity(token)
        val groups = ArrayList<CollectedFirstList>()
        groups.add(CollectedFirstList("已收藏"))
        var childs: ArrayList<ArrayList<Commodity>>
        var adapter: CollectedAdapter
        collectedViewModel.collectedResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        collectedViewModel.commodityList.observe(this) {
            childs = it
            adapter = CollectedAdapter(groups, childs, this, token)
            binding.expandList.setAdapter(adapter)
            binding.expandList.expandGroup(0)
        }

    }
}
