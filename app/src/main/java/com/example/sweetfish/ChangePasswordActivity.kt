package com.example.sweetfish

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityChangePasswordBinding
import com.example.sweetfish.ui.changePassword.ChangePasswordViewModel

class ChangePasswordActivity : AppCompatActivity() {
    private var isButtonClickable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val changePasswordViewModel = ViewModelProvider(this)[ChangePasswordViewModel::class.java]
        val token = intent.getStringExtra("token").toString()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        binding.sendCode.setOnClickListener {
            if (isButtonClickable) {
                changePasswordViewModel.sendChangePasswordCode(
                    token,
                    prefs.getString("mail", "").toString()
                )
                isButtonClickable = false
                startCountdown(binding, 10)
            }

        }
        binding.verify.setOnClickListener {
            changePasswordViewModel.changePassword(
                token,
                binding.editCode.text.toString(),
                binding.editNewPassword.toString()
            )
        }
        changePasswordViewModel.sendChangePasswordCodeResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
        changePasswordViewModel.changePasswordResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            if (it.code == 200) {
                finish()
            }
        }
        binding.back.setOnClickListener {
            finish()
        }
    }

    //倒计时，防止验证码短时间重复发送
    private fun startCountdown(binding: ActivityChangePasswordBinding, seconds: Int) {
        val timer = object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 更新按钮文本显示为倒计时的剩余秒数
                binding.sendCode.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // 倒计时结束，恢复按钮为可点击状态
                isButtonClickable = true
                binding.sendCode.text = "发送验证码" // 恢复按钮文本
            }
        }
        timer.start()
    }
}