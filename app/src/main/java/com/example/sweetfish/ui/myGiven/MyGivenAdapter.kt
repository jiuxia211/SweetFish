package com.example.sweetfish.ui.myGiven

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.DetailActivity
import com.example.sweetfish.databinding.MyGivenItemsBinding
import com.example.sweetfish.utils.commodity.Commodity

class MyGivenAdapter(
    var myGivenList: List<Commodity>, val activity: FragmentActivity,
    val token: String, val myGivenViewModel: MyGivenViewModel
) :
    RecyclerView.Adapter<MyGivenAdapter.ViewHolder>() {
    inner class ViewHolder(binding: MyGivenItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val username: TextView = binding.username
        val price: TextView = binding.price
        val avatar: ImageView = binding.avatar
        val cover: ImageView = binding.cover
        val title: TextView = binding.title
        val verify: Button = binding.verify
        val reject: Button = binding.reject
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MyGivenItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val commodity = myGivenList[position]
            val intent = Intent(parent.context, DetailActivity::class.java).apply {
                putExtra("pid", commodity.id.toString())
                putExtra("token", token)
            }
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commodity = myGivenList[position]
        Glide.with(activity).load(commodity.avatarPath).circleCrop().into(holder.avatar)
        Glide.with(activity).load(commodity.coverPath).circleCrop().into(holder.cover)
        holder.username.text = commodity.username
        holder.title.text = commodity.title
        holder.price.text = "出价${commodity.price}元"
        holder.verify.setOnClickListener {
            myGivenViewModel.confirmTransaction(token, commodity.id.toString(), "1")
        }
        holder.reject.setOnClickListener {
            myGivenViewModel.confirmTransaction(token, commodity.id.toString(), "0")
        }
    }

    override fun getItemCount(): Int {
        return myGivenList.size
    }
}