package com.wangxingxing.videocollections.logic.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wangxingxing.videocollections.logic.model.Discovery
import com.wangxingxing.videocollections.Const
import com.wangxingxing.videocollections.logic.api.HomePageService
import com.wangxingxing.videocollections.logic.model.Daily
import com.wangxingxing.videocollections.logic.model.HomePageRecommend
import com.wangxingxing.videocollections.ui.homepage.daily.DailyPagingSource
import com.wangxingxing.videocollections.ui.homepage.discovery.DiscoveryPagingSource
import com.wangxingxing.videocollections.ui.homepage.recommend.RecommendPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * author : 王星星
 * date : 2022/2/16 16:57
 * email : 1099420259@qq.com
 * description : 首页对应的仓库数据管理。
 */
class HomePageRepositoryImpl constructor(
    private val homePageService: HomePageService
) : HomePageRepository {
    override fun getDiscoveryPagingData(): Flow<PagingData<Discovery.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DiscoveryPagingSource(homePageService) }
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun getHomePageRecommendPagingData(): Flow<PagingData<HomePageRecommend.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { RecommendPagingSource(homePageService) }
        ).flow.flowOn(Dispatchers.IO)
    }

    override fun getDailyPagingData(): Flow<PagingData<Daily.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DailyPagingSource(homePageService)}
        ).flow.flowOn(Dispatchers.IO)
    }
}