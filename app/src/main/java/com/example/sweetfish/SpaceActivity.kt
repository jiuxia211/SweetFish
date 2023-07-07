package com.example.sweetfish

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivitySpaceBinding
import com.example.sweetfish.databinding.SpaceMenuBinding
import com.example.sweetfish.ui.space.SpaceTabAdapter
import com.example.sweetfish.ui.space.SpaceViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SpaceActivity : AppCompatActivity() {
    private lateinit var part: MultipartBody.Part
    private lateinit var popupWindow: PopupWindow
    private var uid = 0
    private var isFollow = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivitySpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token").toString()
        val username = intent.getStringExtra("username").toString()
        initPopupWindow()
        binding.vp.adapter = SpaceTabAdapter(this)
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
        binding.individuation.setOnClickListener {
            popupWindow.showAtLocation(binding.individuation, Gravity.BOTTOM, 0, 0)
        }
        val spaceViewModel = ViewModelProvider(this)[SpaceViewModel::class.java]
        spaceViewModel.initUserInfo(username, token)
        spaceViewModel.userResponseData.observe(this) {
            if (it.code == 200) {
                Glide.with(this).load(it.data.avatar)
                    .circleCrop()
                    .into(binding.avatar)
                binding.fansNum.text = it.data.followed.toString()
                binding.sellNum.text = it.data.turnover.toString()
                if (it.data.isFollowed == 1) {
                    binding.follow.setBackgroundResource(R.color.gray)
                    binding.follow.text = "已关注"
                } else {
                    binding.follow.setBackgroundResource(R.color.teal_200)
                    binding.follow.text = "关注"
                }
                uid = it.data.id
                isFollow = it.data.isFollowed
                binding.username.text = it.data.username
                Glide.with(this).load(it.data.background).into(binding.wallpaperImageView)
            }
        }
        spaceViewModel.setAvatarResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            spaceViewModel.initUserInfo(username, token)
        }
        spaceViewModel.setBackgroundResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            spaceViewModel.initUserInfo(username, token)
        }
        binding.follow.setOnClickListener {
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            val mUid = prefs.getInt("id", -1)
            if (uid == mUid) {
                Toast.makeText(this, "不能关注自己", Toast.LENGTH_SHORT).show()
            } else {
                if (isFollow == 1) {
                    spaceViewModel.follow(uid.toString(), "0", token)
                } else {
                    spaceViewModel.follow(uid.toString(), "1", token)
                }
            }

        }
        spaceViewModel.followResponseData.observe(this) {
            spaceViewModel.initUserInfo(username, token)
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
        binding.sendMsg.setOnClickListener {
            spaceViewModel.addChat(uid.toString(), token)
        }
        spaceViewModel.addChatResponseData.observe(this) {
            if (it.code == 400) {
                Toast.makeText(this, "不能对自己发起对话", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ChatActivity::class.java)
                intent.putExtra("uid", uid)
                intent.putExtra("token", token)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }

    }

    //用于修改头像
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                startCropActivity(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    //用于修改背景
    private val pickMedia1 =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                startCropActivity1(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private fun startCropActivity(imageUri: Uri) {
        val options = UCrop.Options().apply {
            setCompressionFormat(Bitmap.CompressFormat.JPEG)
            setCompressionQuality(90)
            setToolbarColor(ContextCompat.getColor(this@SpaceActivity, R.color.teal_200))
            setStatusBarColor(ContextCompat.getColor(this@SpaceActivity, R.color.teal_200))
            withAspectRatio(1f, 1f) // 设置裁剪比例为 1:1
            withMaxResultSize(800, 800) // 设置裁剪后的图片最大尺寸
            setCircleDimmedLayer(true)
            setToolbarTitle("Crop Avatar")
        }

        clearCropCache()
        //必须有不同的命名，否则图片永远会是同一张
        val croppedImageUri =
            Uri.fromFile(File(cacheDir, "cropped_image" + System.currentTimeMillis() + ".jpg"))
        UCrop.of(imageUri, croppedImageUri)
            .withOptions(options)
            .start(this, avatarCropRequestCode)
    }

    private fun startCropActivity1(imageUri: Uri) {
        val options = UCrop.Options().apply {
            setCompressionFormat(Bitmap.CompressFormat.JPEG)
            setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL)
            setToolbarColor(ContextCompat.getColor(this@SpaceActivity, R.color.teal_200))
            setStatusBarColor(ContextCompat.getColor(this@SpaceActivity, R.color.teal_200))
            setCompressionQuality(90)
            withAspectRatio(1F, 16F / 9F)
            setToolbarTitle("Crop Background")
        }
        clearCropCache()
        //必须有不同的命名，否则图片永远会是同一张
        val croppedImageUri =
            Uri.fromFile(File(cacheDir, "cropped_image" + System.currentTimeMillis() + ".jpg"))
        UCrop.of(imageUri, croppedImageUri)
            .withOptions(options)
            .start(this, wallpaperCropRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == avatarCropRequestCode && resultCode == RESULT_OK) {
            val croppedImageUri = UCrop.getOutput(data!!)
            val avatar = File(croppedImageUri?.path.toString())
            val requestBody = avatar.asRequestBody("image/jp".toMediaTypeOrNull())
            part = MultipartBody.Part.createFormData("files", avatar.name, requestBody)
            val token = intent.getStringExtra("token").toString()
            val spaceViewModel = ViewModelProvider(this)[SpaceViewModel::class.java]
            spaceViewModel.setAvatar(token, part)
        } else if (requestCode == wallpaperCropRequestCode && resultCode == RESULT_OK) {
            val croppedImageUri = UCrop.getOutput(data!!)
            val background = File(croppedImageUri?.path.toString())
            val requestBody = background.asRequestBody("image/jp".toMediaTypeOrNull())
            part = MultipartBody.Part.createFormData("cover", background.name, requestBody)
            val token = intent.getStringExtra("token").toString()
            val spaceViewModel = ViewModelProvider(this)[SpaceViewModel::class.java]
            spaceViewModel.setBackground(token, part)
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
        val menuLayout = SpaceMenuBinding.inflate(layoutInflater)
        popupWindow.contentView = menuLayout.root
        popupWindow.width = WindowManager.LayoutParams.MATCH_PARENT
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.animationStyle = R.style.PopupAnimation
        popupWindow.isOutsideTouchable = true
        menuLayout.setAvatar.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            popupWindow.dismiss()
        }
        menuLayout.setBackground.setOnClickListener {
            pickMedia1.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            popupWindow.dismiss()
        }
    }

    companion object {
        const val wallpaperCropRequestCode = 20
        const val avatarCropRequestCode = 19
    }
}