package com.example.sweetfish.ui.space

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sweetfish.ui.space.collected.CollectedFragment
import com.example.sweetfish.ui.space.published.PublishedFragment
import com.example.sweetfish.ui.space.purchased.PurchasedFragment
import com.example.sweetfish.ui.space.sold.SoldFragment

class TabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PurchasedFragment()
            1 -> CollectedFragment()
            2 -> PublishedFragment()
            else -> SoldFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

}