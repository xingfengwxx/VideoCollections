package com.wangxingxing.videocollections.logic.di

import com.wangxingxing.videocollections.logic.SearchRepository
import com.wangxingxing.videocollections.logic.SearchRepositoryImpl
import com.wangxingxing.videocollections.logic.api.SearchService
import com.wangxingxing.videocollections.logic.db.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * author : 王星星
 * date : 2022/2/16 14:23
 * email : 1099420259@qq.com
 * description :
 */
@InstallIn(ActivityComponent::class)
@Module
object RepositoryModule {

    @ActivityScoped
    @Provides
    fun provideSearchRepository(
        api: SearchService,
        dao: SearchDao
    ): SearchRepository {
        return SearchRepositoryImpl(api, dao)
    }
}