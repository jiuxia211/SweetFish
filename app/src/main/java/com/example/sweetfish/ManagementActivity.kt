package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetfish.databinding.ActivityManagementBinding

class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding = ActivityManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var groups: MutableList<ManagementFirstList> =
//            mutableListOf(
//                ManagementFirstList("待审核"),
//                ManagementFirstList("用户申诉"),
//                ManagementFirstList("塔尔斯")
//            )
//
//        var adapter = ManagementAdapter(groups, childs, this)
//        binding.expandList.setAdapter(adapter)
    }
}