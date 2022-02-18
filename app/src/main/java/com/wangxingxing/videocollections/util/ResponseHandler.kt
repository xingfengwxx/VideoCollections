package com.wangxingxing.videocollections.util

import com.google.gson.JsonSyntaxException
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.extension.logW
import com.wangxingxing.videocollections.ui.common.exception.ResponseCodeException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * author : 王星星
 * date : 2022/2/17 10:15
 * email : 1099420259@qq.com
 * description : 获取网络请求返回的异常信息。
 */
object ResponseHandler {

    private const val TAG = "ResponseHandler"

    fun getFailureTips(e: Throwable?): String {
        logW(TAG, "getFailureTips exception is ${e?.message}")
        return when(e) {
            is ConnectException -> GlobalUtil.getString(R.string.network_connect_error)
            is SocketTimeoutException -> GlobalUtil.getString(R.string.network_connect_timeout)
            is ResponseCodeException -> GlobalUtil.getString(R.string.network_response_code_error) + e.responseCode
            is NoRouteToHostException -> GlobalUtil.getString(R.string.no_route_to_host)
            is UnknownHostException -> GlobalUtil.getString(R.string.network_error)
            is JsonSyntaxException -> GlobalUtil.getString(R.string.json_data_error)
            else -> {
                GlobalUtil.getString(R.string.unknown_error)
            }
        }
    }
}