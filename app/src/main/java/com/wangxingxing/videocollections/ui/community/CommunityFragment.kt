package com.wangxingxing.videocollections.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.ui.common.ui.BaseViewPagerFragment
import com.wangxingxing.videocollections.util.GlobalUtil

/**
 * author : 王星星
 * date : 2022/2/15 17:10
 * email : 1099420259@qq.com
 * description : 社区
 */
class CommunityFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<String>().apply {
        add(GlobalUtil.getString(R.string.recommend))
        add(GlobalUtil.getString(R.string.follow))
    }

    override val createFragments: Array<Fragment> = arrayOf(
//        RecommendFragment.newInstance(),
//        FollowFragment.newInstance()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container, container, false))
    }

}