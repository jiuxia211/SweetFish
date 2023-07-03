package com.example.sweetfish.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sweetfish.PhotoActivity
import com.example.sweetfish.databinding.ImageItemsBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageAdapter(
    private val imageUrls: MutableList<String>,
    private val activity: AppCompatActivity,
) :
    SliderViewAdapter<ImageAdapter.SliderViewHolder>() {

    fun updateData(newUrls: List<String>) {
        imageUrls.clear()
        imageUrls.addAll(newUrls)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val binding =
            ImageItemsBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        val imageUrl = imageUrls[position]
        viewHolder?.imageView?.let { Glide.with(activity).load(imageUrl).into(it) }
        viewHolder?.itemView?.setOnClickListener {
            val intent = Intent(activity, PhotoActivity::class.java).apply {
                putExtra("url", imageUrl)
            }
            activity.startActivity(intent)
        }
    }

    override fun getCount(): Int {
        return imageUrls.size
    }

    inner class SliderViewHolder(binding: ImageItemsBinding) :
        ViewHolder(binding.root) {
        val imageView: ImageView = binding.image
    }
}






