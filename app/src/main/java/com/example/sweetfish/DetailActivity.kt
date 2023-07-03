package com.example.sweetfish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivityDetailBinding
import com.example.sweetfish.databinding.InputPriceBinding
import com.example.sweetfish.ui.detail.DetailViewModel
import com.example.sweetfish.ui.detail.ImageAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class DetailActivity : AppCompatActivity() {
    private lateinit var priceInputDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val token = intent.getStringExtra("token").toString()
        val pid = intent.getStringExtra("pid").toString()
        val adapter = ImageAdapter(ArrayList<String>(), this)
        binding.sliderView.apply {
            setSliderAdapter(adapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            startAutoCycle()
            scrollTimeInSec = 4
        }
        binding.avatar.setOnClickListener {
            val intent = Intent(this, SpaceActivity::class.java)
            intent.putExtra("token", token)
            intent.putExtra("username", binding.username.text)
            startActivity(intent)
        }
        val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.getDetail(token, pid)
        detailViewModel.detailJsonData.observe(this) {
            binding.title.text = it.data.detail.title
            binding.content.text = it.data.detail.content
            binding.price.text = it.data.detail.price.toString()
            binding.username.text = it.data.detail.username
            adapter.updateData(it.data.detail.pic_urls)
            detailViewModel.initUserInfo(it.data.detail.username, token)
            if (it.data.detail.isFav == 1) {
                binding.collect.setImageResource(R.drawable.collected1)
            } else {
                binding.collect.setImageResource(R.drawable.collected)
            }
        }
        detailViewModel.userResponseData.observe(this) {
            Glide.with(this).load(it.data.avatar)
                .circleCrop()
                .into(binding.avatar)
        }
        binding.givePrice.setOnClickListener {
            showPriceInputDialog()
        }
        binding.collect.setOnClickListener {
            detailViewModel.collect(pid, token)
        }
        detailViewModel.collectJsonData.observe(this) {
            if (it.data == "1") {
                binding.collect.setImageResource(R.drawable.collected1)
            } else {
                binding.collect.setImageResource(R.drawable.collected)
            }
        }
    }

    private fun showPriceInputDialog() {
        val inputPriceBinding = InputPriceBinding.inflate(layoutInflater)
        priceInputDialog =
            AlertDialog.Builder(this).setView(inputPriceBinding.root)
                .setPositiveButton("确认") { dialog, _ ->
                    val inputPrice = inputPriceBinding.numberEditText.text.toString()
                    dialog.dismiss()
                }.setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }.create()
        priceInputDialog.show()
    }
}