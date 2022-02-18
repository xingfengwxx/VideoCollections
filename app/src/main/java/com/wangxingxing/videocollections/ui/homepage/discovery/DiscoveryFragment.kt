package com.wangxingxing.videocollections.ui.homepage.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.wangxingxing.videocollections.databinding.FragmentRefreshLayoutBinding
import com.wangxingxing.videocollections.ui.common.ui.BaseFragment
import com.wangxingxing.videocollections.ui.common.ui.FooterAdapter
import com.wangxingxing.videocollections.util.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * author : 王星星
 * date : 2022/2/16 16:46
 * email : 1099420259@qq.com
 * description : 发现
 */
@AndroidEntryPoint
class DiscoveryFragment : BaseFragment() {

    private lateinit var binding: FragmentRefreshLayoutBinding

    private val viewModel: DiscoveryViewModel by viewModels()

    private lateinit var adapter: DiscoveryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRefreshLayoutBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiscoveryAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        // 这里的 Footer 无法起作用，因为分页逻辑中，是当做只有一页处理的
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(FooterAdapter { adapter.retry()})
        // 下拉刷新
        binding.refreshLayout.setOnRefreshListener { adapter.refresh() }
        addLoadStateListener()

        lifecycleScope.launchWhenCreated {
            viewModel.getPagingData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun loadFinished() {
        super.loadFinished()
        binding.refreshLayout.finishRefresh()
    }

    private fun addLoadStateListener() {
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    loadFinished()
                    // 到了最后一页，显示 “The End” NoStatusFooter
                    if (it.source.append.endOfPaginationReached) {
                        // 显示
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        // 不显示
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {
                    if (binding.refreshLayout.state != RefreshState.Refreshing) {
                        startLoading()
                    }
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    loadFailed(ResponseHandler.getFailureTips(state.error))
                }
            }
        }
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}