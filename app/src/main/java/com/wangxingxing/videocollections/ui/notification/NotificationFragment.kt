package com.wangxingxing.videocollections.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.ui.common.ui.BaseViewPagerFragment
import com.wangxingxing.videocollections.util.GlobalUtil
import dagger.hilt.android.AndroidEntryPoint

/**
 * author : 王星星
 * date : 2022/2/15 17:19
 * email : 1099420259@qq.com
 * description : 通知
 */
@AndroidEntryPoint
class NotificationFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<String>().apply {
        add(GlobalUtil.getString(R.string.push))
        add(GlobalUtil.getString(R.string.interaction))
        add(GlobalUtil.getString(R.string.inbox))
    }

    override val createFragments: Array<Fragment> = arrayOf(
//        PushFragment.newInstance(),
//        InteractionFragment.newInstance(),
//        InboxFragment.newInstance()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container, container, false))
    }

    companion object {

        fun newInstance() = NotificationFragment()
    }
}