package com.example.sweetfish.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sweetfish.ui.search.search_commodity.CommodityFragment
import com.example.sweetfish.ui.search.search_user.UserFragment

class SearchTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CommodityFragment()
            else -> UserFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}