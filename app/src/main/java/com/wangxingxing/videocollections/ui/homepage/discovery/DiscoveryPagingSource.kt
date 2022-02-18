package com.wangxingxing.videocollections.ui.homepage.discovery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.videocollections.logic.model.Discovery
import com.wangxingxing.videocollections.logic.api.HomePageService
import java.lang.Exception

/**
 * author : 王星星
 * date : 2022/2/16 17:04
 * email : 1099420259@qq.com
 * description :
 */
class DiscoveryPagingSource(private val homePageService: HomePageService) : PagingSource<String, Discovery.Item>() {

    override fun getRefreshKey(state: PagingState<String, Discovery.Item>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Discovery.Item> {
        return try {
            val page = params.key ?: HomePageService.DISCOVERY_URL
            val repoResponse = homePageService.getDiscovery(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}