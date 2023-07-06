package com.example.sweetfish.ui.my

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweetfish.*
import com.example.sweetfish.databinding.FragmentMyBinding

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
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
        val prefs = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        initUserInfo(prefs)
        myViewModel.userResponseData.observe(this) {
            if (it.code == 200) {
                binding.username.text = it.data.username
                binding.email.text = it.data.mail
                if (it.data.permission == 1) {
                    binding.userType.text = "管理员"
                } else {
                    binding.userType.text = "正式会员"
                }
                binding.money.text = "余额: ${it.data.balance}"
                Glide.with(this).load(it.data.avatar)
                    .placeholder(R.drawable.loading)
                    .circleCrop()
                    .into(binding.avatar)
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
        binding.purchasedImg.setOnClickListener {
            val intent = Intent(activity, PurchasedActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.collected.setOnClickListener {
            val intent = Intent(activity, CollectActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.collectedImg.setOnClickListener {
            val intent = Intent(activity, CollectActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.publishedImg.setOnClickListener {
            val intent = Intent(activity, PublishedActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.published.setOnClickListener {
            val intent = Intent(activity, PublishedActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.sold.setOnClickListener {
            val intent = Intent(activity, SoldActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.sellImg.setOnClickListener {
            val intent = Intent(activity, SoldActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.management.setOnClickListener {
            val intent = Intent(activity, ManagementActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        binding.space.setOnClickListener {
            val intent = Intent(activity, SpaceActivity::class.java)
            intent.putExtra("token", token)
            intent.putExtra("username", username)
            startActivity(intent)
        }
        binding.logOut.setOnClickListener {

            activity?.finish()
        }
        binding.setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUserInfo(prefs: SharedPreferences?) {
        prefs?.apply {
            binding.username.text = getString("username", "")
            binding.email.text = getString("mail", "")
            if (getInt("permission", 0) == 1) {
                binding.userType.text = "管理员"
            } else {
                binding.userType.text = "正式会员"
            }
            binding.money.text = "余额: ${getFloat("balance", -1f)}"
            Glide.with(this@MyFragment).load(getString("avatar", ""))
                .circleCrop()
                .into(binding.avatar)
            if (getInt("permission", 0) != 1) {
                binding.management.visibility = View.INVISIBLE
            }
        }
    }
}