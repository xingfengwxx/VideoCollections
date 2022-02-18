package com.wangxingxing.videocollections.ui.community.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.databinding.FragmentRefreshLayoutBinding
import com.wangxingxing.videocollections.extension.gone
import com.wangxingxing.videocollections.extension.showToast
import com.wangxingxing.videocollections.extension.visible
import com.wangxingxing.videocollections.ui.common.callback.AutoPlayScrollListener
import com.wangxingxing.videocollections.ui.common.ui.BaseFragment
import com.wangxingxing.videocollections.ui.common.ui.FooterAdapter
import com.wangxingxing.videocollections.util.GlobalUtil
import com.wangxingxing.videocollections.util.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2022/2/18 12:02
 * email : 1099420259@qq.com
 * description : 社区 → 关注
 */
@AndroidEntryPoint
class FollowFragment : BaseFragment() {

    private lateinit var binding: FragmentRefreshLayoutBinding

    private val viewModel: FollowViewModel by viewModels()

    private lateinit var adapter: FollowAdapter

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
        adapter = FollowAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = null
        binding.recyclerView.addOnScrollListener(
            AutoPlayScrollListener(
                R.id.videoPlayer,
                AutoPlayScrollListener.PLAY_RANGE_TOP,
                AutoPlayScrollListener.PLAY_RANGE_BOTTOM
            )
        )
        binding.refreshLayout.setOnRefreshListener { adapter.refresh() }
        binding.refreshLayout.gone()
        addLoadStateListener()

        lifecycleScope.launch {
            viewModel.getPagingData().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    override fun startLoading() {
        super.startLoading()
        binding.refreshLayout.gone()
    }

    override fun loadFinished() {
        super.loadFinished()
        binding.refreshLayout.visible()
        binding.refreshLayout.finishRefresh()
    }

    @CallSuper
    override fun loadFailed(msg: String?) {
        super.loadFailed(msg)
        binding.refreshLayout.finishRefresh()
        showLoadErrorView(msg ?: GlobalUtil.getString(R.string.unknown_error)) {
            startLoading()
            adapter.refresh()
        }
    }

    private fun addLoadStateListener() {
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    loadFinished()
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
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

        adapter.addLoadStateListener {
            when (it.append) {
                is LoadState.NotLoading -> {
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    val state = it.append as LoadState.Error
                    ResponseHandler.getFailureTips(state.error).showToast()
                }
            }
        }
    }

    companion object {
        fun newInstance() = FollowFragment()
    }
}