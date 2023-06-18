package com.example.sweetfish.ui.management

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ManagementItemsBinding
import com.example.sweetfish.ui.detail.ImageAdapter
import com.example.sweetfish.utils.commodity.CommodityDetail


class ManagementAdapter(
    private val commodityList: List<CommodityDetail>,
    private val activity: AppCompatActivity
) :
    RecyclerView.Adapter<ManagementAdapter.GroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding =
            ManagementItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = GroupViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            //TODO 点击事件
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val commodity = commodityList[position]
        holder.commodityTitle.text = commodity.title
        holder.commodityContent.text = commodity.content
        holder.price.text = commodity.price
        holder.username.text = commodity.username
        Glide.with(activity).load(commodity.avatarPath).circleCrop().into(holder.avatar)
        holder.images.adapter = ImageAdapter(commodity.pic_urls.toMutableList(), activity)
    }

    override fun getItemCount(): Int {
        return commodityList.size
    }

    inner class GroupViewHolder(binding: ManagementItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val commodityTitle: TextView = binding.title
        val commodityContent: TextView = binding.content
        val username: TextView = binding.username
        val price: TextView = binding.price
        val avatar: ImageView = binding.avatar
        val images: ViewPager2 = binding.images
    }
}
