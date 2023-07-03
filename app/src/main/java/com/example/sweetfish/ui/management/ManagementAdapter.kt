package com.example.sweetfish.ui.management

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ManagementItemsBinding
import com.example.sweetfish.ui.detail.ImageAdapter
import com.example.sweetfish.utils.commodity.CommodityDetail
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderView


class ManagementAdapter(
    private var commodityList: List<CommodityDetail>,
    private val activity: AppCompatActivity,
    private val managementViewModel: ManagementViewModel,
    private val token: String
) :
    RecyclerView.Adapter<ManagementAdapter.GroupViewHolder>() {
    private lateinit var imageAdapter: ImageAdapter
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
        imageAdapter = ImageAdapter(commodity.pic_urls.toMutableList(), activity)
        holder.images.apply {
            setSliderAdapter(imageAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            startAutoCycle()
        }
        holder.pass.setOnClickListener {
            managementViewModel.Audit(token, commodity.id.toString(), "审核通过", "1")
        }
        holder.reject.setOnClickListener {
            managementViewModel.Audit(
                token,
                commodity.id.toString(),
                holder.rejectInfo.text.toString(),
                "0"
            )
        }
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
        val images: SliderView = binding.imageSlider
        val pass: Button = binding.pass
        val reject: Button = binding.reject
        val rejectInfo: EditText = binding.rejectInfo
    }

    fun updateData(newCommodityList: List<CommodityDetail>) {
        commodityList = newCommodityList
        notifyDataSetChanged()
    }
}
