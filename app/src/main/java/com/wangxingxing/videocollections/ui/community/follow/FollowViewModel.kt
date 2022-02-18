package com.wangxingxing.videocollections.ui.community.follow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.videocollections.logic.model.Follow
import com.wangxingxing.videocollections.logic.repository.CommunityRepository
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/18 12:04
 * email : 1099420259@qq.com
 * description :
 */
class FollowViewModel @ViewModelInject constructor(
    val repository: CommunityRepository
) : ViewModel() {

    var dataList = ArrayList<Follow.Item>()

    fun getPagingData(): Flow<PagingData<Follow.Item>> {
        return repository.getFollowPagingData().cachedIn(viewModelScope)
    }
}