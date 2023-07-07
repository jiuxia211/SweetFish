package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityManagementBinding
import com.example.sweetfish.ui.management.ManagementAdapter
import com.example.sweetfish.ui.management.ManagementViewModel
import com.example.sweetfish.utils.commodity.CommodityDetail
import com.google.android.material.tabs.TabLayoutMediator

class ManagementActivity : AppCompatActivity() {
    private val commodityDetailList: MutableList<CommodityDetail> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        val managementViewModel = ViewModelProvider(this)[ManagementViewModel::class.java]
        managementViewModel.getAuditCommodity(token)
        managementViewModel.commodityList.observe(this) {
            commodityDetailList.clear()
            for (i in it) {
                managementViewModel.getDetail(token, i)
            }
        }
        managementViewModel.auditResponseData.observe(this) {
            managementViewModel.getAuditCommodity(token)
        }
        val adapter = ManagementAdapter(commodityDetailList, this, managementViewModel, token)
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
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Commodity ${position + 1}"
        }.attach()
    }
}