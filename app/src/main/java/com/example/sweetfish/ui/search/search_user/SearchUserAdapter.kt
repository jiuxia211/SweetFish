package com.example.sweetfish.ui.search.search_user

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.SpaceActivity
import com.example.sweetfish.databinding.SearchUserItemsBinding
import com.example.sweetfish.ui.search.SearchViewModel
import com.example.sweetfish.utils.user.SearchUser

class SearchUserAdapter(
    var searchUserList: List<SearchUser>, val activity: FragmentActivity,
    val token: String, val searchViewModel: SearchViewModel
) :
    RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {
    inner class ViewHolder(binding: SearchUserItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val username: TextView = binding.username
        val fansNum: TextView = binding.fansNum
        val avatar: ImageView = binding.avatar
        val turnover: TextView = binding.turnover
        val follow: Button = binding.follow
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchUserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val user = searchUserList[position]
            val intent = Intent(parent.context, SpaceActivity::class.java).apply {
                putExtra("username", user.username)
                putExtra("token", token)
            }
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = searchUserList[position]
        Glide.with(activity).load(user.avatar).circleCrop().into(holder.avatar)
        holder.fansNum.text = "${user.followed}粉丝"
        holder.turnover.text = "完成${user.turnover}单"
        holder.username.text = user.username
        holder.follow.setOnClickListener {
            val prefs = activity.getSharedPreferences("user", Context.MODE_PRIVATE)
            val mUid = prefs.getInt("id", -1)
            if (user.id == mUid) {
                Toast.makeText(activity, "不能关注自己", Toast.LENGTH_SHORT).show()
            } else {
//                if (isFollow == 1) {
//                    spaceViewModel.follow(uid.toString(), "0", token)
//                } else {
//                    spaceViewModel.follow(uid.toString(), "1", token)
//                }
            }
            searchViewModel.follow(user.id.toString(), "1", token)
        }
    }

    override fun getItemCount(): Int {
        return searchUserList.size
    }
}
