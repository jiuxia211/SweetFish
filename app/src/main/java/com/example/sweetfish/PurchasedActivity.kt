package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityPurchasedBinding
import com.example.sweetfish.ui.purchased.PurchasedViewModel

class PurchasedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPurchasedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val purchasedViewModel = ViewModelProvider(this)[PurchasedViewModel::class.java]
        purchasedViewModel.commodityList.observe(this) {

        }
        purchasedViewModel.purchasedResponseData.observe(this) {

        }
    }
}