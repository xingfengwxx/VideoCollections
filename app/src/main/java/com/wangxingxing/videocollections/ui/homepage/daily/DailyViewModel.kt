package com.wangxingxing.videocollections.ui.homepage.daily

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.videocollections.logic.model.Daily
import com.wangxingxing.videocollections.logic.repository.HomePageRepository
import kotlinx.coroutines.flow.Flow

class DailyViewModel @ViewModelInject constructor(
    val repository: HomePageRepository
) : ViewModel() {

    var dataList = ArrayList<Daily.Item>()

    fun getPagingData(): Flow<PagingData<Daily.Item>> {
        return repository.getDailyPagingData().cachedIn(viewModelScope)
    }
}
