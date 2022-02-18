package com.wangxingxing.videocollections.ui.homepage.discovery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.videocollections.logic.model.Discovery
import com.wangxingxing.videocollections.logic.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/16 16:49
 * email : 1099420259@qq.com
 * description :
 */
class DiscoveryViewModel @ViewModelInject constructor(
    val repository: HomePageRepository
) : ViewModel() {

    fun getPagingData(): Flow<PagingData<Discovery.Item>> {
        return repository.getDiscoveryPagingData().cachedIn(viewModelScope)
    }
}