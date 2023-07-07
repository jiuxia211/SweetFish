package com.example.sweetfish.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.sweetfish.R
import com.example.sweetfish.SearchActivity
import com.example.sweetfish.databinding.FragmentMainBinding
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        val username = activity?.intent?.getStringExtra("username").toString()
        //初始化RecyclerView
        val layoutManager = GridLayoutManager(activity, 2)
        binding.commodities.layoutManager = layoutManager
        val adapter = RecommendAdapter(ArrayList<Commodity>(), activity!!, token)
        binding.commodities.adapter = adapter
        mainViewModel.initUserInfo(username, token)
        mainViewModel.initRecommendInfo(token)
        mainViewModel.userResponseData.observe(this) {
            if (it.code == 200) {
                Glide.with(this).load(it.data.avatar)
                    .placeholder(R.drawable.loading)
                    .circleCrop()
                    .into(binding.avatar)
                //缓存用户数据
                val editor = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)?.edit()
                editor?.apply {
                    putString("avatar", it.data.avatar)
                    putString("background", it.data.background)
                    putString("mail", it.data.mail)
                    putString("username", it.data.username)
                    putFloat("balance", it.data.balance)
                    putInt("turnover", it.data.turnover)
                    putInt("follow", it.data.follow)
                    putInt("followed", it.data.followed)
                    putInt("id", it.data.id)
                    putInt("real", it.data.real)
                    putInt("permission", it.data.permission)
                    apply()
                }
            }
        }
        mainViewModel.commodityList.observe(this) {
            val result =
                DiffUtil.calculateDiff(CommodityDiffCallback(adapter.commodityList, it), true)
            adapter.commodityList = it
            result.dispatchUpdatesTo(adapter)
        }
        binding.searchPanel.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.Refresh.setOnRefreshListener {
            mainViewModel.initRecommendInfo(token)
            binding.Refresh.isRefreshing = false

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}