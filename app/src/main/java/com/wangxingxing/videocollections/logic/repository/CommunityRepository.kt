package com.wangxingxing.videocollections.logic.repository

import androidx.paging.PagingData
import com.wangxingxing.videocollections.logic.model.CommunityRecommend
import com.wangxingxing.videocollections.logic.model.Follow
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/18 12:04
 * email : 1099420259@qq.com
 * description :
 */
interface CommunityRepository {

    fun getCommunityRecommendPagingData(): Flow<PagingData<CommunityRecommend.Item>>

    fun getFollowPagingData(): Flow<PagingData<Follow.Item>>
}