package com.example.sweetfish

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivityDetailBinding
import com.example.sweetfish.databinding.InputPriceBinding
import com.example.sweetfish.ui.detail.DetailViewModel
import com.example.sweetfish.ui.detail.ImageAdapter
import com.example.sweetfish.utils.socketEvent.Message
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import org.greenrobot.eventbus.EventBus

class DetailActivity : AppCompatActivity() {
    private lateinit var priceInputDialog: AlertDialog
    private var uid = 0
    private var isFollow = -1
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
            binding.username.text = it.data.username
            if (it.data.isFollowed == 1) {
                binding.follow.setBackgroundResource(R.color.gray)
                binding.follow.text = "已关注"
            } else {
                binding.follow.setBackgroundResource(R.color.teal_200)
                binding.follow.text = "关注"
            }
            uid = it.data.id
            isFollow = it.data.isFollowed
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
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        binding.follow.setOnClickListener {
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            val mUid = prefs.getInt("id", -1)
            if (uid == mUid) {
                Toast.makeText(this, "不能关注自己", Toast.LENGTH_SHORT).show()
            } else {
                if (isFollow == 1) {
                    detailViewModel.follow(uid.toString(), "0", token)
                } else {
                    detailViewModel.follow(uid.toString(), "1", token)
                }
            }
        }
        detailViewModel.followResponseData.observe(this) {
            detailViewModel.initUserInfo(binding.username.text.toString(), token)
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        binding.contact.setOnClickListener {
            detailViewModel.addChat(uid.toString(), token)
        }
        detailViewModel.addChatResponseData.observe(this) {
            if (it.code == 400) {
                Toast.makeText(this, "不能对自己发起对话", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("uid", uid)
                intent.putExtra("token", token)
                intent.putExtra("username", binding.username.text)
                startActivity(intent)
            }
        }
        detailViewModel.givePriceResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPriceInputDialog() {
        val inputPriceBinding = InputPriceBinding.inflate(layoutInflater)
        priceInputDialog =
            AlertDialog.Builder(this).setView(inputPriceBinding.root)
                .setPositiveButton("确认") { dialog, _ ->
                    val inputPrice = inputPriceBinding.numberEditText.text.toString()
                    val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
                    val token = intent.getStringExtra("token").toString()
                    val pid = intent.getStringExtra("pid").toString()
                    Log.d("zz", pid)
                    detailViewModel.givePrice(token, pid, inputPrice)
                    detailViewModel.addChat(uid.toString(), token)
                    val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
                    val mUid = prefs.getInt("id", -1)
                    EventBus.getDefault().post(Message(mUid, "系统消息:我已经对你的商品进行的出价，请确认", uid))
                    dialog.dismiss()
                }.setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }.create()
        priceInputDialog.show()

    }
}