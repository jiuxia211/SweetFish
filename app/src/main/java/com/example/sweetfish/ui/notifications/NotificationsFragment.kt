package com.example.sweetfish.ui.notifications

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.sweetfish.databinding.FragmentNotificationsBinding
import com.example.sweetfish.utils.user.ChatListUser
import com.example.sweetfish.utils.user.ChatListUserDiffCallback

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        val username = activity?.intent?.getStringExtra("username").toString()
        notificationsViewModel.getChatList(token)
        //初始化RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        binding.users.layoutManager = layoutManager
        val adapter = ChatListAdapter(ArrayList<ChatListUser>(), activity!!, token)
        binding.users.adapter = adapter
        notificationsViewModel.chatList.observe(this) {
            val result = DiffUtil.calculateDiff(ChatListUserDiffCallback(adapter.chatList, it))
            adapter.chatList = it
            result.dispatchUpdatesTo(adapter)
        }
        val prefs = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        initUserInfo(prefs)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUserInfo(prefs: SharedPreferences?) {
        prefs?.apply {
            Glide.with(this@NotificationsFragment).load(getString("avatar", ""))
                .circleCrop()
                .into(binding.avatar)

        }
    }
}