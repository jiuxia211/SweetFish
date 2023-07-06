package com.example.sweetfish.ui.search.search_commodity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetfish.databinding.FragmentSearchCommodityBinding
import com.example.sweetfish.ui.search.SearchViewModel
import com.example.sweetfish.utils.commodity.SearchCommodity
import com.example.sweetfish.utils.commodity.SearchCommodityDiffCallback

class CommodityFragment : Fragment() {
    private var _binding: FragmentSearchCommodityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel = ViewModelProvider(activity!!)[SearchViewModel::class.java]
        _binding = FragmentSearchCommodityBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        val layoutManager = LinearLayoutManager(activity)
        binding.commodities.layoutManager = layoutManager
        val adapter =
            SearchCommodityAdapter(ArrayList<SearchCommodity>(), activity!!, token, searchViewModel)
        binding.commodities.adapter = adapter
        searchViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(
                    SearchCommodityDiffCallback(adapter.searchCommodityList, it),
                    true
                )
            adapter.searchCommodityList = it
            result.dispatchUpdatesTo(adapter)
        }
        return root
    }
}