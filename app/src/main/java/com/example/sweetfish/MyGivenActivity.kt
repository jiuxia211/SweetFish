package com.example.sweetfish

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetfish.databinding.ActivityMyGivenBinding
import com.example.sweetfish.ui.myGiven.MyGivenAdapter
import com.example.sweetfish.ui.myGiven.MyGivenViewModel
import com.example.sweetfish.utils.commodity.Commodity
import com.example.sweetfish.utils.commodity.CommodityDiffCallback
import com.google.android.material.snackbar.Snackbar

class MyGivenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyGivenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val myGivenViewModel = ViewModelProvider(this)[MyGivenViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        myGivenViewModel.getMyGiven(token)
        val layoutManager = LinearLayoutManager(this)
        binding.commodities.layoutManager = layoutManager
        val adapter = MyGivenAdapter(ArrayList<Commodity>(), this, token, myGivenViewModel)
        binding.commodities.adapter = adapter
        myGivenViewModel.confirmResponseData.observe(this) {
            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
            myGivenViewModel.getMyGiven(token)
        }
        myGivenViewModel.myGivenList.observe(this) {
            val result =
                DiffUtil.calculateDiff(
                    CommodityDiffCallback(adapter.myGivenList, it),
                    true
                )
            adapter.myGivenList = it
            result.dispatchUpdatesTo(adapter)
        }
        myGivenViewModel.addChatResponseData.observe(this) {
            if (it.code == 400) {
                Toast.makeText(this, "不能对自己发起对话", Toast.LENGTH_SHORT).show()
            }
        }
    }
}