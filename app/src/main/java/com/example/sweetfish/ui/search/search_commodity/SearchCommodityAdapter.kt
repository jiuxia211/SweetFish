package com.example.sweetfish.ui.search.search_commodity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.DetailActivity
import com.example.sweetfish.databinding.SearchCommodityItemsBinding
import com.example.sweetfish.ui.search.SearchViewModel
import com.example.sweetfish.utils.commodity.SearchCommodity

class SearchCommodityAdapter(
    var searchCommodityList: List<SearchCommodity>, val activity: FragmentActivity,
    val token: String, val searchViewModel: SearchViewModel
) :
    RecyclerView.Adapter<SearchCommodityAdapter.ViewHolder>() {
    inner class ViewHolder(binding: SearchCommodityItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val username: TextView = binding.username
        val buyer: TextView = binding.buyer
        val avatar: ImageView = binding.avatar
        val cover: ImageView = binding.cover
        val title: TextView = binding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchCommodityItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val commodity = searchCommodityList[position]
            val intent = Intent(parent.context, DetailActivity::class.java).apply {
                putExtra("pid", commodity.post_id.toString())
                putExtra("token", token)
            }
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commodity = searchCommodityList[position]
        Glide.with(activity).load(commodity.avatar).circleCrop().into(holder.avatar)
        Glide.with(activity).load(commodity.cover).circleCrop().into(holder.cover)
        holder.username.text = commodity.username
        holder.title.text = commodity.title
        holder.buyer.text = "${commodity.now_buyer}人已出价"
    }

    override fun getItemCount(): Int {
        return searchCommodityList.size
    }
}