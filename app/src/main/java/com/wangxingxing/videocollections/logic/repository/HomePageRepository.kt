package com.wangxingxing.videocollections.logic.repository

import androidx.paging.PagingData
import com.wangxingxing.videocollections.logic.model.Daily
import com.wangxingxing.videocollections.logic.model.Discovery
import com.wangxingxing.videocollections.logic.model.HomePageRecommend
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/16 16:50
 * email : 1099420259@qq.com
 * description :
 */
interface HomePageRepository {

    fun getDiscoveryPagingData(): Flow<PagingData<Discovery.Item>>

    fun getHomePageRecommendPagingData(): Flow<PagingData<HomePageRecommend.Item>>

    fun getDailyPagingData(): Flow<PagingData<Daily.Item>>
}