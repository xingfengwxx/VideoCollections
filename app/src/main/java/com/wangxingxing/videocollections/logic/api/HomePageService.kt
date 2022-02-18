package com.wangxingxing.videocollections.logic.api

import com.wangxingxing.videocollections.logic.model.Daily
import com.wangxingxing.videocollections.logic.model.Discovery
import com.wangxingxing.videocollections.logic.model.HomePageRecommend
import com.wangxingxing.videocollections.logic.di.NetWorkModule
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * 主页界面，主要包含：（首页，社区，通知，我的）对应的 API 接口。
 */
interface HomePageService {

    /**
     * 首页-发现列表
     */
    @GET
    suspend fun getDiscovery(@Url url: String): Discovery

    /**
     * 首页-推荐列表
     */
    @GET
    suspend fun getHomePageRecommend(@Url url: String): HomePageRecommend

    /**
     * 首页-日报列表
     */
    @GET
    suspend fun getDaily(@Url url: String): Daily

    companion object {

        /**
         * 首页-发现列表
         */
        const val DISCOVERY_URL = "${NetWorkModule.BASE_URL}api/v7/index/tab/discovery"

        /**
         * 首页-推荐列表
         */
        const val HOMEPAGE_RECOMMEND_URL = "${NetWorkModule.BASE_URL}api/v5/index/tab/allRec?page=0"

        /**
         * 首页-日报列表
         */
        const val DAILY_URL = "${NetWorkModule.BASE_URL}api/v5/index/tab/feed"

        /**
         * 社区-推荐列表
         */
        const val COMMUNITY_RECOMMEND_URL = "${NetWorkModule.BASE_URL}api/v7/community/tab/rec"

        /**
         * 社区-关注列表
         */
        const val FOLLOW_URL = "${NetWorkModule.BASE_URL}api/v6/community/tab/follow"

        /**
         * 通知-推送列表
         */
        const val PUSHMESSAGE_URL = "${NetWorkModule.BASE_URL}api/v3/messages"
    }
}