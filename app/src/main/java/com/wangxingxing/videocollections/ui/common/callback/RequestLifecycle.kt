package com.wangxingxing.videocollections.ui.common.callback

/**
 * author : 王星星
 * date : 2022/2/15 16:18
 * email : 1099420259@qq.com
 * description : 在Activity或Fragment中进行网络请求所需要经历的生命周期函数。
 */
interface RequestLifecycle {

    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)
}