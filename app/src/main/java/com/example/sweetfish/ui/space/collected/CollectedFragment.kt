package com.example.sweetfish.ui.space.collected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.FragmentCollectedBinding
import com.example.sweetfish.ui.collect.CollectedAdapter
import com.example.sweetfish.ui.collect.CollectedFirstList
import com.example.sweetfish.ui.collect.CollectedViewModel
import com.example.sweetfish.utils.commodity.Commodity

class CollectedFragment : Fragment() {
    private var _binding: FragmentCollectedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val collectedViewModel = ViewModelProvider(this)[CollectedViewModel::class.java]
        val token = activity?.intent?.getStringExtra("token").toString()
        //加载已收藏的商品信息
        collectedViewModel.initCollectCommodity(token)
        val groups = ArrayList<CollectedFirstList>()
        groups.add(CollectedFirstList("已收藏"))
        var childs: ArrayList<ArrayList<Commodity>>
        var adapter: CollectedAdapter
        collectedViewModel.collectedResponseData.observe(this) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }
        collectedViewModel.commodityList.observe(this) {
            childs = it
            adapter = CollectedAdapter(groups, childs, activity!!, token)
            binding.expandList.setAdapter(adapter)
            binding.expandList.expandGroup(0)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}