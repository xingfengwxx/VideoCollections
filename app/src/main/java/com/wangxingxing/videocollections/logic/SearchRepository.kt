package com.wangxingxing.videocollections.logic

import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/16 10:58
 * email : 1099420259@qq.com
 * description :
 */
interface SearchRepository {
    fun getHotSearch(): Flow<List<String>>
}