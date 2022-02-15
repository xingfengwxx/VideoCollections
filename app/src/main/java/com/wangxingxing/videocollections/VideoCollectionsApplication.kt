package com.wangxingxing.videocollections

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager
import dagger.hilt.android.HiltAndroidApp
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * author : 王星星
 * date : 2022/2/15 14:46
 * email : 1099420259@qq.com
 * description :
 */
@HiltAndroidApp
class VideoCollectionsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        IjkPlayerManager.setLogLevel(if (BuildConfig.DEBUG) IjkMediaPlayer.IJK_LOG_WARN else IjkMediaPlayer.IJK_LOG_SILENT)
    }

    init {
        // 设置默认 Refresh 初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}