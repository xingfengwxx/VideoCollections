package com.wangxingxing.videocollections.logic.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wangxingxing.videocollections.Const
import com.wangxingxing.videocollections.logic.api.CommunityService
import com.wangxingxing.videocollections.logic.model.CommunityRecommend
import com.wangxingxing.videocollections.logic.model.Follow
import com.wangxingxing.videocollections.ui.community.follow.FollowPagingSource
import com.wangxingxing.videocollections.ui.community.recommend.RecommendPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2022/2/18 12:06
 * email : 1099420259@qq.com
 * description :
 */
class CommunityRepositoryImpl constructor(
    private val communityService: CommunityService
) : CommunityRepository {

    override fun getCommunityRecommendPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { RecommendPagingSource(communityService) }
        ).flow
    }

    override fun getFollowPagingData(): Flow<PagingData<Follow.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { FollowPagingSource(communityService) }
        ).flow
    }
}