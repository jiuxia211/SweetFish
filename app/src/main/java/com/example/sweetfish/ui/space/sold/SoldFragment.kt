package com.example.sweetfish.ui.space.sold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sweetfish.databinding.FragmentSoldBinding
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class SoldFragment : Fragment() {

    private var _binding: FragmentSoldBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSoldBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        //加载已购买的商品信息
        val soldViewModel = ViewModelProvider(this)[SoldViewModel::class.java]
        soldViewModel.initSoldCommodity(token)
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(activity, 2)
        binding.commodities.layoutManager = layoutManager
        var adapter = SoldAdapter(ArrayList<Commodity>(), activity!!, token)
        binding.commodities.adapter = adapter
        soldViewModel.soldResponseData.observe(this) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }
        //观察商品列表是否改变，改变则用DiffUtil刷新recycleview
        soldViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(CommodityDiffCallback(adapter.commodityList, it), true)
            adapter.commodityList = it
            result.dispatchUpdatesTo(adapter)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}