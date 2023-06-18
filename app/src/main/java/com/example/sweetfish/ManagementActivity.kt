package com.example.sweetfish

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityManagementBinding
import com.example.sweetfish.ui.management.ManagementAdapter
import com.example.sweetfish.ui.management.ManagementViewModel
import com.example.sweetfish.utils.commodity.CommodityDetail

class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        val commodityDetailList: MutableList<CommodityDetail> = mutableListOf()
        commodityDetailList.add(
            CommodityDetail(
                0,
                "title",
                "content",
                "26",
                "https://c-ssl.duitang.com/uploads/blog/202103/08/20210308233851_9365f.jpg",
                "https://c-ssl.duitang.com/uploads/blog/202103/08/20210308233851_9365f.jpg",
                "jiuxia",
                ArrayList<String>()
            )
        )
        val adapter = ManagementAdapter(commodityDetailList, this)
        val managementViewModel = ViewModelProvider(this)[ManagementViewModel::class.java]
        managementViewModel.getAuditCommodity(token)
        managementViewModel.commodityList.observe(this) {
            for (i in it) {
                managementViewModel.getDetail(token, i)
                Log.d("zz", "获取")
            }
        }
        managementViewModel.detailJsonData.observe(this) {
            commodityDetailList.add(
                CommodityDetail(
                    it.data.detail.post_id,
                    it.data.detail.title,
                    it.data.detail.content,
                    it.data.detail.price.toString(),
                    it.data.detail.cover,
                    it.data.detail.avatar,
                    it.data.detail.username,
                    it.data.detail.pic_urls,
                )
            )
            adapter.notifyDataSetChanged()
        }

        binding.recyclerView.adapter = adapter
        binding.viewPager.adapter = adapter
    }
}