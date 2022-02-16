package com.wangxingxing.videocollections.logic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wangxingxing.videocollections.logic.entity.HotSearchEntity

/**
 * author : 王星星
 * date : 2022/2/16 15:25
 * email : 1099420259@qq.com
 * description : 应用程序所有Dao操作管理类。
 */
@Database(
    entities = [HotSearchEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

}