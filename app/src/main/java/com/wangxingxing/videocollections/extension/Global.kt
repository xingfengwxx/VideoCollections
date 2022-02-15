package com.wangxingxing.videocollections.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.VideoCollectionsApplication
import com.wangxingxing.videocollections.ui.common.ui.ShareDialogFragment
import com.wangxingxing.videocollections.util.GlobalUtil
import com.wangxingxing.videocollections.util.ShareUtil

/**
 * 获取DataStore实例。
 */
val dataStore: DataStore<Preferences> = VideoCollectionsApplication.context.dataStore

/**
 * 获取SharedPreferences实例。
 */
val sharedPreferences: SharedPreferences = VideoCollectionsApplication.context.getSharedPreferences(
    GlobalUtil.appPackage + "_preferences", Context.MODE_PRIVATE
)


/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param block 处理点击事件回调代码块
 */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { it?.setOnClickListener(listener) }
}
/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param listener 处理点击事件监听器
 */
fun setOnClickListener(vararg v: View?, listener: View.OnClickListener) {
    v.forEach { it?.setOnClickListener(listener) }
}


/**
 * 调用系统原生分享。
 *
 * @param activity 上下文
 * @param shareContent 分享内容
 * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
 */
fun share(activity: Activity, shareContent: String, shareType: Int) {
    ShareUtil.share(activity, shareContent, shareType)
}

/**
 * 弹出分享对话框。
 *
 * @param activity 上下文
 * @param shareContent 分享内容
 */
fun showDialogShare(activity: Activity, shareContent: String) {
    if (activity is AppCompatActivity) {
        ShareDialogFragment().showDialog(activity, shareContent)
    }
}