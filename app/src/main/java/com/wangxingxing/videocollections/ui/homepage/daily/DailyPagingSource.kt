package com.wangxingxing.videocollections.ui.homepage.daily

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.videocollections.logic.api.HomePageService
import com.wangxingxing.videocollections.logic.model.Daily

class DailyPagingSource(private val homePageService: HomePageService) :
    PagingSource<String, Daily.Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Daily.Item> {
        return try {
            val page = params.key ?: HomePageService.DAILY_URL
            val repoResponse = homePageService.getDaily(page)
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

    override fun getRefreshKey(state: PagingState<String, Daily.Item>): String? = null
}