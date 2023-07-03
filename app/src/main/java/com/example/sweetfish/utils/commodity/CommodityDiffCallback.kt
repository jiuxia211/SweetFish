package com.example.sweetfish.utils.commodity

import androidx.recyclerview.widget.DiffUtil

class CommodityDiffCallback(
    private val oldCommodities: List<Commodity>,
    private val newCommodities: List<Commodity>
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
        return old.title == new.title
    }
}