package com.example.sweetfish.ui.space.sold

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.DetailActivity
import com.example.sweetfish.databinding.SoldItemsBinding
import com.example.sweetfish.utils.commodity.Commodity

class SoldAdapter(
    var commodityList: List<Commodity>,
    val activity: FragmentActivity,
    val token: String
) :
    RecyclerView.Adapter<SoldAdapter.ViewHolder>() {
    inner class ViewHolder(binding: SoldItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val commodityTitle: TextView = binding.title
        val commodityCover: ImageView = binding.cover
        val avatar: ImageView = binding.avatar
        val username: TextView = binding.username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SoldItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val commodity = commodityList[position]
            val intent = Intent(parent.context, DetailActivity::class.java).apply {
                putExtra("pid", commodity.id.toString())
                putExtra("token", token)
            }
            parent.context.startActivity(intent)
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