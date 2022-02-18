package com.wangxingxing.videocollections.ui.community.follow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wangxingxing.videocollections.logic.api.CommunityService
import com.wangxingxing.videocollections.logic.model.Follow

/**
 * author : 王星星
 * date : 2022/2/18 12:23
 * email : 1099420259@qq.com
 * description :
 */
class FollowPagingSource(private val communityService: CommunityService) : PagingSource<String, Follow.Item>() {

    override fun getRefreshKey(state: PagingState<String, Follow.Item>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Follow.Item> {
        return try {
            val page = params.key ?: CommunityService.FOLLOW_URL
            val repoResponse = communityService.getFollow(page)
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