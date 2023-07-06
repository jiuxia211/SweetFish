package com.example.sweetfish.ui.space.published

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetfish.databinding.FragmentPublishedBinding
import com.example.sweetfish.ui.published.PublishedAdapter
import com.example.sweetfish.ui.published.PublishedViewModel
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class PublishedFragment : Fragment() {

    private var _binding: FragmentPublishedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublishedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val publishedViewModel = ViewModelProvider(this)[PublishedViewModel::class.java]
        val token = activity?.intent?.getStringExtra("token").toString()
        //加载已购买的商品信息
        publishedViewModel.initPublishedCommodity(token)
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(activity, 2)
        binding.commodities.layoutManager = layoutManager
        var adapter = PublishedAdapter(ArrayList<Commodity>(), activity!!, token)
        binding.commodities.adapter = adapter
        publishedViewModel.purchasedResponseData.observe(this) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }
        //观察商品列表是否改变，改变则用DiffUtil刷新recycleview
        publishedViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(CommodityDiffCallback(adapter.commodityList, it), true)
            adapter.commodityList = it
            result.dispatchUpdatesTo(adapter)
            if (it.size == 0) {
                binding.nullText.visibility = View.VISIBLE
            } else {
                binding.nullText.visibility = View.GONE
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}