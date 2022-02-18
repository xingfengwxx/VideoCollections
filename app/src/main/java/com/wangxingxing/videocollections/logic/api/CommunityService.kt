package com.wangxingxing.videocollections.logic.api

import com.wangxingxing.videocollections.logic.di.NetWorkModule
import com.wangxingxing.videocollections.logic.model.CommunityRecommend
import com.wangxingxing.videocollections.logic.model.Follow
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * author : 王星星
 * date : 2022/2/18 12:07
 * email : 1099420259@qq.com
 * description : 社区
 */
interface CommunityService {

    /**
     * 社区-推荐列表
     */
    @GET
    suspend fun getCommunityRecommend(@Url url: String): CommunityRecommend

    @GET
    suspend fun getFollow(@Url url: String): Follow

    companion object {
        /**
         * 社区-推荐列表
         */
        const val COMMUNITY_RECOMMEND_URL = "${NetWorkModule.BASE_URL}api/v7/community/tab/rec"

        /**
         * 社区-关注列表
         */
        const val FOLLOW_URL = "${NetWorkModule.BASE_URL}api/v6/community/tab/follow"

    }
}