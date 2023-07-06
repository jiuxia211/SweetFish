package com.example.sweetfish.utils.commodity

import androidx.recyclerview.widget.DiffUtil

class SearchCommodityDiffCallback(
    private val oldCommodities: List<SearchCommodity>,
    private val newCommodities: List<SearchCommodity>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCommodities[oldItemPosition].post_id == newCommodities[newItemPosition].post_id
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