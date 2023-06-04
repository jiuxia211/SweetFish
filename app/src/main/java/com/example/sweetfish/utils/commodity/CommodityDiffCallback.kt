package com.example.sweetfish.utils.commodity

import androidx.recyclerview.widget.DiffUtil

class CommodityDiffCallback(
    val oldCommodities: List<Commodity>,
    val newCommodities: List<Commodity>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCommodities[oldItemPosition].id == newCommodities[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldCommodities.size
    }

    override fun getNewListSize(): Int {
        return newCommodities.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldCommodities[oldItemPosition]
        val new = newCommodities[newItemPosition]
//        return old.title == new.title && old.coverPath == new.coverPath
        return old.title == new.title
        //&& old.imageId == new.imageId && old.imageId == new.avatarId
    }
}