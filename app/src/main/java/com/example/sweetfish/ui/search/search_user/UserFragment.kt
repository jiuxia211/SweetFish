package com.example.sweetfish.ui.search.search_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetfish.databinding.FragmentSearchUserBinding
import com.example.sweetfish.ui.search.SearchViewModel
import com.example.sweetfish.utils.user.SearchUser
import com.example.sweetfish.utils.user.SearchUserDiffCallback

class UserFragment : Fragment() {
    private var _binding: FragmentSearchUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel = ViewModelProvider(activity!!)[SearchViewModel::class.java]
        _binding = FragmentSearchUserBinding.inflate(layoutInflater, container, false)
        val root: View = binding.root
        val token = activity?.intent?.getStringExtra("token").toString()
        val layoutManager = LinearLayoutManager(activity)
        binding.users.layoutManager = layoutManager
        val adapter =
            SearchUserAdapter(ArrayList<SearchUser>(), activity!!, token, searchViewModel)
        binding.users.adapter = adapter
        searchViewModel.userList.observe(this) {
            val result =
                DiffUtil.calculateDiff(SearchUserDiffCallback(adapter.searchUserList, it), true)
            adapter.searchUserList = it
            result.dispatchUpdatesTo(adapter)
        }
        return root
    }
}