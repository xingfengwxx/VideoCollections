package com.wangxingxing.videocollections.ui.common.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wangxingxing.videocollections.R

/**
 * author : 王星星
 * date : 2022/2/15 16:17
 * email : 1099420259@qq.com
 * description : TabLayout + ViewPager2 + Fragment 基类
 */
abstract class BaseViewPagerFragment : BaseFragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null
    private val adapter: VpAdapter by lazy { VpAdapter(requireActivity()).apply { addFragments(createFragments) } }

    abstract val createTitles: ArrayList<String>
    abstract val createFragments: Array<Fragment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    protected fun initViewPager() {
        viewPager = rootView?.findViewById(R.id.viewPager)
        tabLayout = rootView?.findViewById(R.id.tabLayout)
        viewPager?.adapter = adapter

        // TabLayout 与 ViewPager 联动
        viewPager?.let {
            tabLayout?.let { it1 ->
                TabLayoutMediator(it1, it) { tab, position ->
                    tab.text = createTitles[position]
                }.attach()
            }
        }
    }

    inner class VpAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        private val fragments = mutableListOf<Fragment>()

        fun addFragments(fragment: Array<Fragment>) {
            fragments.addAll(fragment)
        }

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]
    }
}