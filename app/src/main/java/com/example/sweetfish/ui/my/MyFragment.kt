package com.example.sweetfish.ui.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.PurchasedActivity
import com.example.sweetfish.UploadActivity
import com.example.sweetfish.databinding.FragmentMyBinding

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myViewModel =
            ViewModelProvider(this)[MyViewModel::class.java]
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        val username = activity?.intent?.getStringExtra("username").toString()
        myViewModel.initUserInfo(username, token)
        myViewModel.userResponseData.observe(this) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == 200) {
                binding.username.text = it.data.username
                binding.email.text = it.data.mail
                if (it.data.permission == 1) {
                    binding.userType.text = "管理员"
                } else {
                    binding.userType.text = "正式会员"
                }
                binding.money.text = "余额: ${it.data.balance}"
                Glide.with(this).load("http://${it.data.avatar}").into(binding.avatar)
            }
        }
        binding.upload.setOnClickListener {
            val intent = Intent(activity, UploadActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.purchased.setOnClickListener {
            val intent = Intent(activity, PurchasedActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}