package com.wangxingxing.videocollections.ui.homepage.recommend

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.videocollections.logic.api.HomePageService
import com.wangxingxing.videocollections.logic.model.HomePageRecommend

class RecommendPagingSource(private val homePageService: HomePageService) :
    PagingSource<String, HomePageRecommend.Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, HomePageRecommend.Item> {
        return try {
            val page = params.key ?: HomePageService.HOMEPAGE_RECOMMEND_URL
            val repoResponse = homePageService.getHomePageRecommend(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey =
                if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, HomePageRecommend.Item>): String? = null
}