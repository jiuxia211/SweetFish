package com.example.sweetfish

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweetfish.databinding.ActivityRegisterBinding
import com.example.sweetfish.ui.register.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private var isButtonClickable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        registerViewModel.registerResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                finish()
            }
        }
        registerViewModel.sendCodeResponseData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }

        binding.sendCode.setOnClickListener {
            if (isButtonClickable) {
                if (binding.editMail.text.isEmpty()) {
                    Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show()
                } else {
                    registerViewModel.sendCode(binding.editMail.text.toString())
                }
                isButtonClickable = false
                startCountdown(binding, 10)
            }
        }
        binding.register.setOnClickListener {
            if (binding.editCode.text.isEmpty()) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show()
            } else {

                registerViewModel.register(
                    binding.editUsername.text.toString(), binding.editPassword.text.toString(),
                    binding.editMail.text.toString(), binding.editCode.text.toString()
                )
            }
        }
    }

    //倒计时，防止验证码短时间重复发送
    private fun startCountdown(binding: ActivityRegisterBinding, seconds: Int) {
        val timer = object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 更新按钮文本显示为倒计时的剩余秒数
                val secondsLeft = millisUntilFinished / 1000
                binding.sendCode.text = secondsLeft.toString()
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