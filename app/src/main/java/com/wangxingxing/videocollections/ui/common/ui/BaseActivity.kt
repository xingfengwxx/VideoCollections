package com.wangxingxing.videocollections.ui.common.ui

import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.util.ShareUtil
import com.wangxingxing.videocollections.extension.showDialogShare


/**
 * author : 王星星
 * date : 2022/2/15 15:24
 * email : 1099420259@qq.com
 * description : 应用程序中所有Activity的基类。
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    protected open fun setupViews() {

    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        immersionBar {
            // 自动状态栏字体变色，必须指定状态栏颜色才可以自动变色
            autoStatusBarDarkModeEnable(true, 0.2f)
            // 状态栏颜色，不写默认透明色
            statusBarColor(statusBarColor)
            // 解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
            fitsSystemWindows(true)
        }
    }

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    protected fun share(shareContent: String, shareType: Int) {
        ShareUtil.share(this, shareContent, shareType)
    }

    /**
     * 弹出分享对话框
     *
     * @param shareContent 分享内容
     */
    protected fun showDialogShare(shareContent: String) {
        showDialogShare(this, shareContent)
    }
}