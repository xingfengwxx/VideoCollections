package com.wangxingxing.videocollections.logic.api

import retrofit2.http.GET

/**
 * author : 王星星
 * date : 2022/2/16 14:11
 * email : 1099420259@qq.com
 * description : 主页界面，主要包含：（首页，社区，通知，我的）对应的 API 接口。
 */
interface SearchService {

    @GET("api/v3/queries/hot")
    suspend fun getHotSearch(): List<String>
}