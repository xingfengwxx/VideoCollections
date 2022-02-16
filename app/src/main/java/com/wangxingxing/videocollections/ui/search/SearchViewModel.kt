package com.wangxingxing.videocollections.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.wangxingxing.videocollections.logic.SearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/16 10:55
 * email : 1099420259@qq.com
 * description :
 */
class SearchViewModel @ViewModelInject constructor(
    repository: SearchRepository
): ViewModel() {

    var dataList = ArrayList<String>()

    val data: Flow<List<String>> = repository.getHotSearch()
}