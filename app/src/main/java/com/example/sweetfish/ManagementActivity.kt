package com.example.sweetfish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetfish.databinding.ActivityManagementBinding
import com.example.sweetfish.ui.management.Commodity
import com.example.sweetfish.ui.management.ManagementAdapter
import com.example.sweetfish.ui.management.ManagementFirstList

class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var groups: MutableList<ManagementFirstList> =
            mutableListOf(
                ManagementFirstList("待审核"),
                ManagementFirstList("塔尔斯")
            )
        var childs: MutableList<MutableList<Commodity>> =
            mutableListOf(
                mutableListOf(
                    Commodity(
                        0,
                        "测试标题",
                        R.drawable.back1,
                        R.drawable.default_avatar,
                        "jiuxia211"
                    )
                ),
                mutableListOf(
                    Commodity(
                        1,
                        "生气",
                        R.drawable.angry,
                        R.drawable.default_avatar,
                        "jiuxia211"
                    ),
                    Commodity(
                        2,
                        "困困",
                        R.drawable.sleepy,
                        R.drawable.default_avatar,
                        "jiuxia211"
                    )
                )
            )
        var adapter = ManagementAdapter(groups, childs)
        binding.expandList.setAdapter(adapter)
    }
}