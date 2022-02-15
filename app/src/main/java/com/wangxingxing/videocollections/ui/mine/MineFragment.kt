package com.wangxingxing.videocollections.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.databinding.FragmentMineBinding
import com.wangxingxing.videocollections.ui.common.ui.BaseFragment
import com.wangxingxing.videocollections.util.GlobalUtil

/**
 * author : 王星星
 * date : 2022/2/15 17:22
 * email : 1099420259@qq.com
 * description : 我的
 */
class MineFragment : BaseFragment() {

    lateinit var binding: FragmentMineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Version %1$s， 1 代表是第一个参数 %s 代表字符串
        binding.tvVersionNumber.text = String.format(
            GlobalUtil.getString(R.string.version_show),
            GlobalUtil.eyepetizerVersionName
        )
    }

    companion object {

        fun newInstance() = MineFragment()
    }
}