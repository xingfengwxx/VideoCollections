package com.wangxingxing.videocollections.logic.di

import android.app.Application
import androidx.room.Room
import com.wangxingxing.videocollections.logic.db.AppDatabase
import com.wangxingxing.videocollections.logic.db.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * author : 王星星
 * date : 2022/2/16 15:18
 * email : 1099420259@qq.com
 * description :
 */
@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "video_collections.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideSearchDao(database: AppDatabase): SearchDao {
        return database.searchDao()
    }
}