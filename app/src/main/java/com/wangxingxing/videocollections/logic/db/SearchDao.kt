package com.wangxingxing.videocollections.logic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wangxingxing.videocollections.logic.entity.HotSearchEntity

/**
 * author : 王星星
 * date : 2022/2/16 14:14
 * email : 1099420259@qq.com
 * description : 主页界面（主要包含：首页，社区，通知，我的），对应的Dao操作类。
 */
@Dao
interface SearchDao {

    /*----------------------------搜索相关----------------------------*/
    @Query("DELETE FROM HotSearchEntity")
    suspend fun clearHotSearch()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheHotSearch(bean: List<HotSearchEntity>?)
}