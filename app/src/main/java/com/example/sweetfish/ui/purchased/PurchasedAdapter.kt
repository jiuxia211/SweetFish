package com.example.sweetfish.ui.purchased

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.PurchasedItemsBinding

class PurchasedAdapter(var commodityList: List<Commodity>, val activity: AppCompatActivity) :
    RecyclerView.Adapter<PurchasedAdapter.ViewHolder>() {
    inner class ViewHolder(binding: PurchasedItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val commodityTitle: TextView = binding.title
        val commodityCover: ImageView = binding.cover
        val avatar: ImageView = binding.avatar
        val username: TextView = binding.username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PurchasedItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            //TODO 点击事件
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commodity = commodityList[position]
        holder.commodityTitle.text = commodity.title
        holder.username.text = commodity.username
        Glide.with(activity).load(commodity.coverPath).into(holder.commodityCover)
        Glide.with(activity).load(commodity.avatarPath).circleCrop().into(holder.avatar)
    }

    override fun getItemCount(): Int {
        return commodityList.size
    }

}