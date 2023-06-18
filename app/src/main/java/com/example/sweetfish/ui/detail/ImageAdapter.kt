package com.example.sweetfish.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ImageItemBinding

class ImageAdapter(private var imageUrls: MutableList<String>, private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        Glide.with(context)
            .load(imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    inner class ImageViewHolder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.image
    }

    fun updateData(newImageUrls: List<String>) {
        imageUrls.clear()
        imageUrls.addAll(newImageUrls)
        notifyItemRangeChanged(0, itemCount)
    }
}