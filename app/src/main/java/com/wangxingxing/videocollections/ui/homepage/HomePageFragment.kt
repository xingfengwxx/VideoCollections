package com.wangxingxing.videocollections.ui.homepage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.databinding.FragmentHomePageBinding
import com.wangxingxing.videocollections.ui.common.ui.BaseViewPagerFragment
import com.wangxingxing.videocollections.ui.homepage.daily.DailyFragment
import com.wangxingxing.videocollections.ui.homepage.discovery.DiscoveryFragment
import com.wangxingxing.videocollections.ui.homepage.recommend.RecommendFragment
import com.wangxingxing.videocollections.ui.search.SearchActivity
import com.wangxingxing.videocollections.util.GlobalUtil
import dagger.hilt.android.AndroidEntryPoint

/**
 * author : 王星星
 * date : 2022/2/15 16:51
 * email : 1099420259@qq.com
 * description : 首页
 */
@AndroidEntryPoint
class HomePageFragment: BaseViewPagerFragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var ivSearch: ImageView

    override val createTitles = ArrayList<String>().apply {
        add(GlobalUtil.getString(R.string.discovery))
        add(GlobalUtil.getString(R.string.recommend))
        add(GlobalUtil.getString(R.string.daily))
    }

    override val createFragments: Array<Fragment> = arrayOf(
        DiscoveryFragment.newInstance(),
        RecommendFragment.newInstance(),
        DailyFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 搜索
        ivSearch = binding.titleBar.ivSearch
        ivSearch.setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
            activity.overridePendingTransition(R.anim.anl_push_up_in, 0)
        }
    }
}