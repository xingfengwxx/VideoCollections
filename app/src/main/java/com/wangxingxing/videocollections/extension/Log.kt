package com.wangxingxing.videocollections.extension

import android.util.Log
import androidx.annotation.IntDef
import com.wangxingxing.videocollections.BuildConfig

/**
 * author : 王星星
 * date : 2022/2/15 15:39
 * email : 1099420259@qq.com
 * description : 日志调试工具类。
 */
private const val VERBOSE = 1
private const val DEBUG = 2
private const val INFO = 3
private const val WARN = 4
private const val ERROR = 5

private val level = if (BuildConfig.DEBUG) VERBOSE else WARN

@IntDef(VERBOSE, DEBUG, INFO, WARN, ERROR)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class TYPE

private const val MAX_LOG_LENGTH = 2000

private const val SEGMENT_SIZE = 3 * 1024

fun logV(tag: String, msg: String?) {
    if (level <= VERBOSE) {
        log(VERBOSE, tag, msg)
    }
}

fun logD(tag: String, msg: String?) {
    if (level <= DEBUG) {
        log(DEBUG, tag, msg)
    }
}

fun logI(tag: String, msg: String?) {
    if (level <= INFO) {
        log(INFO, tag, msg)
    }
}

fun logW(tag: String, msg: String?, tr: Throwable? = null) {
    if (level <= WARN) {
        log(WARN, tag, msg, tr)
    }
}

fun logE(tag: String, msg: String?, tr: Throwable) {
    if (level <= ERROR) {
        log(ERROR, tag, msg, tr)
    }
}

private fun log(@TYPE type: Int, tag: String?, msg: String?, tr: Throwable? = null) {
    //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
    // 把4*1024的MAX字节打印长度改为2001字符数
    val maxStrLength = 2001 - tag?.length!!
    //大于4000时
    var str = msg
    while (str?.length!! > maxStrLength) {
        realLog(type, tag, str?.substring(0, maxStrLength), tr)
        str = str.substring(maxStrLength)
    }
    //剩余部分
    realLog(type, tag, str, tr)
}

private fun realLog(@TYPE type: Int, tag: String?, msg: String?, tr: Throwable? = null) {
    when (type) {
        VERBOSE -> Log.v(tag, msg.toString())
        DEBUG -> Log.d(tag, msg.toString())
        INFO -> Log.i(tag, msg.toString())
        WARN -> {
            if (tr == null) {
                Log.w(tag, msg.toString())
            } else {
                Log.w(tag, msg.toString(), tr)
            }
        }
        ERROR -> {
            if (tr == null) {
                Log.e(tag, msg.toString())
            } else {
                Log.e(tag, msg.toString(), tr)
            }
        }
    }
}

