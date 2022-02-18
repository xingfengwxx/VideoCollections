package com.wangxingxing.videocollections.ui.common.exception


/**
 * author : 王星星
 * date : 2022/2/17 10:19
 * email : 1099420259@qq.com
 * description : 当服务器响应的头不在200与300之间时，说明请求出现了异常，此时应该将此异常主动抛出。
 */
class ResponseCodeException(val responseCode: Int) : RuntimeException("Http request failed with response code $responseCode") {
}