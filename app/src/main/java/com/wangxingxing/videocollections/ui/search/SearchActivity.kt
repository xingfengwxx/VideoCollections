package com.wangxingxing.videocollections.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wangxingxing.videocollections.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * author : 王星星
 * date : 2022/2/15 17:27
 * email : 1099420259@qq.com
 * description : 搜索
 */
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}