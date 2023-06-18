package com.example.sweetfish

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivitySpaceBinding
import com.example.sweetfish.databinding.AvatarMenuBinding
import com.example.sweetfish.ui.space.SpaceViewModel
import com.example.sweetfish.ui.space.TabAdapter
import com.example.sweetfish.utils.URIPathHelper
import com.google.android.material.tabs.TabLayoutMediator
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SpaceActivity : AppCompatActivity() {
    private lateinit var part: MultipartBody.Part
    private lateinit var popupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivitySpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        val username = intent.getStringExtra("username").toString()
        initPopupWindow()
        binding.vp.adapter = TabAdapter(this)
        TabLayoutMediator(binding.tb, binding.vp) { tab, position ->
            when (position) {
                0 -> tab.text = "购买"
                1 -> tab.text = "收藏"
                2 -> tab.text = "发布"
                else -> tab.text = "卖出"
            }
        }.attach()
        //点击头像的显示popupWindow菜单
        binding.avatar.setOnClickListener {
            popupWindow.showAtLocation(binding.avatar, Gravity.BOTTOM, 0, 0)
        }
        val spaceViewModel = ViewModelProvider(this)[SpaceViewModel::class.java]
        spaceViewModel.initUserInfo(username, token)
        spaceViewModel.userResponseData.observe(this) {
            if (it.code == 200) {
                Glide.with(this).load(it.data.avatar)
                    .placeholder(R.drawable.loading)
                    .circleCrop()
                    .into(binding.avatar)
            }
        }
        spaceViewModel.setAvatarResponseData.observe(this) {
            if (it.code == 200) {
                finish()
            }
        }


    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            startCropActivity(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    private fun startCropActivity(imageUri: Uri) {
        val options = UCrop.Options().apply {
            setCompressionFormat(Bitmap.CompressFormat.JPEG)
            setCompressionQuality(90)
            withAspectRatio(1f, 1f) // 设置裁剪比例为 1:1
            withMaxResultSize(800, 800) // 设置裁剪后的图片最大尺寸
            setCircleDimmedLayer(true)
        }
        clearCropCache()
        //必须有不同的命名，否则图片永远会是同一张
        val croppedImageUri =
            Uri.fromFile(File(cacheDir, "cropped_image" + System.currentTimeMillis() + ".jpg"))
        UCrop.of(imageUri, croppedImageUri)
            .withOptions(options)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            val croppedImageUri = UCrop.getOutput(data!!)
            Log.d("zz", croppedImageUri.toString())
            val helper = URIPathHelper()
            val path = croppedImageUri?.let { helper.getPath(this, it) }
            val avatar = File(path.toString())
            val requestBody = avatar.asRequestBody("image/jp".toMediaTypeOrNull())
            part = MultipartBody.Part.createFormData("files", avatar.name, requestBody)
            val token = intent.getStringExtra("token").toString()
            val spaceViewModel = ViewModelProvider(this)[SpaceViewModel::class.java]
            spaceViewModel.setAvatar(token, part)
        }
    }

    private fun clearCropCache() {
        val cacheDir = cacheDir
        if (cacheDir != null && cacheDir.isDirectory) {
            val files = cacheDir.listFiles()
            if (files != null) {
                Log.d("zz", "files not null")
                for (file in files) {
                    Log.d("zz", file.name)
                    val isSuccess = file.delete()
                    Log.d("zz", isSuccess.toString())
                }
            }
        }
    }

    private fun initPopupWindow() {
        popupWindow = PopupWindow(this)
        val menuLayout = AvatarMenuBinding.inflate(layoutInflater)
        popupWindow.contentView = menuLayout.root
        popupWindow.width = WindowManager.LayoutParams.MATCH_PARENT
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.animationStyle = R.style.PopupAnimation
        popupWindow.isOutsideTouchable = true
        menuLayout.setAvatar.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}