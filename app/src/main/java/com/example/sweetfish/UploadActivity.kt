package com.example.sweetfish

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.ActivityUploadAccountBinding
import com.example.sweetfish.ui.upload.UploadViewModel
import com.example.sweetfish.utils.URIPathHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class UploadActivity : AppCompatActivity() {
    private var cnt = 1//计算传入的图片数
    private var pathList = ArrayList<String>()//先将path加入pathList
    private var fileList = ArrayList<File>()//再将path转成文件
    private var parts = ArrayList<MultipartBody.Part>()//再转成part
    val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                pathList.clear()
                for (i in uris) {
                    val helper = URIPathHelper()
                    val path = helper.getPath(this, i)
                    pathList.add(path.toString())
                    //将图片载入控件
                    when (cnt) {
                        1 -> {
                            val picture: ImageView = findViewById(R.id.image1)
                            Glide.with(this).load(i).into(picture)
                            cnt++
                        }
                        2 -> {
                            val picture: ImageView = findViewById(R.id.image2)
                            Glide.with(this).load(i).into(picture)
                            cnt++
                        }
                        3 -> {
                            val picture: ImageView = findViewById(R.id.image3)
                            Glide.with(this).load(i).into(picture)
                            cnt++
                        }
                        4 -> {
                            val picture: ImageView = findViewById(R.id.image4)
                            Glide.with(this).load(i).into(picture)
                            cnt++
                        }
                        5 -> {
                            val picture: ImageView = findViewById(R.id.image5)
                            Glide.with(this).load(i).into(picture)
                            cnt++
                        }
                    }
                }

            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityUploadAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uploadViewModel = ViewModelProvider(this)[UploadViewModel::class.java]
        uploadViewModel.uploadResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                finish()
            }
        }

        val token = intent.getStringExtra("token").toString()
        binding.choosePicture.setOnClickListener {
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.upload.setOnClickListener {
            fileList.clear()
            for (i in pathList) {
                val file = File(i)
                fileList.add(file)
            }
            var cnt1 = 1
            for (i in fileList) {
                Log.d("zz", cnt1.toString())
                Log.d("zz", "file$cnt1")
                Log.d("zz", i.name)
                val requestBody = i.asRequestBody("image/jp".toMediaTypeOrNull())
                val part = MultipartBody.Part.createFormData("file$cnt1", i.name, requestBody)
                parts.add(part)
                cnt1++
            }
            val title =
                binding.editTitle.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val content =
                binding.editContent.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val price =
                binding.editPrice.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val account =
                binding.editAccount.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val password =

                binding.editPassword.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            Log.d("zz", "正在上传")
            uploadViewModel.upload(
                token, title, content, price, account, password, parts
            )

        }
    }

}