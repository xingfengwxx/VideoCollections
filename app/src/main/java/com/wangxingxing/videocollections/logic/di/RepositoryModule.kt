package com.wangxingxing.videocollections.logic.di

import com.wangxingxing.videocollections.logic.api.CommunityService
import com.wangxingxing.videocollections.logic.api.HomePageService
import com.wangxingxing.videocollections.logic.api.SearchService
import com.wangxingxing.videocollections.logic.api.VideoService
import com.wangxingxing.videocollections.logic.db.SearchDao
import com.wangxingxing.videocollections.logic.repository.*
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

    @ActivityScoped
    @Provides
    fun provideHomePageRepository(
        api: HomePageService
    ): HomePageRepository {
        return HomePageRepositoryImpl(api)
    }

    @ActivityScoped
    @Provides
    fun provideVideoRepository(
        api: VideoService
    ): VideoRepository {
        return VideoRepositoryImpl(api)
    }

    @ActivityScoped
    @Provides
    fun provideCommunityRepository(
        api: CommunityService
    ): CommunityRepository {
        return CommunityRepositoryImpl(api)
    }
}