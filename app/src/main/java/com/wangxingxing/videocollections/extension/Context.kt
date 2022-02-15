package com.wangxingxing.videocollections.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.wangxingxing.videocollections.VideoCollectionsApplication

/**
 * author : 王星星
 * date : 2022/2/15 15:44
 * email : 1099420259@qq.com
 * description : 获取DataStore实例
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = VideoCollectionsApplication.context.packageName + "_preferences",
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, VideoCollectionsApplication.context.packageName + "_preferences"))
    }
)