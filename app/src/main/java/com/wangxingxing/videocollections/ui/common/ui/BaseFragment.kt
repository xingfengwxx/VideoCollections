package com.wangxingxing.videocollections.ui.common.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.extension.logD
import com.wangxingxing.videocollections.ui.common.callback.RequestLifecycle

/**
 * author : 王星星
 * date : 2022/2/15 16:17
 * email : 1099420259@qq.com
 * description :
 */
open class BaseFragment : Fragment(), RequestLifecycle {

    /**
     * 依附的Activity
     */
    lateinit var activity: Activity

    /**
     * Fragment中inflate出来的布局。
     */
    protected var rootView: View? = null

    /**
     * Fragment中由于服务器或网络异常导致加载失败显示的布局。
     */
    private var loadErrorView: View? = null

    /**
     * Fragment中显示加载等待的控件。
     */
    protected var loading: ProgressBar? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 缓存当前依附的activity
        activity = requireActivity()
        logD(TAG, "BaseFragment-->onAttach()")
    }

    /**
     * 在Fragment基类中获取通用的控件，会将传入的View实例原封不动返回。
     * @param view Fragment中inflate出来的View实例。
     * @return  Fragment中inflate出来的View实例原封不动返回。
     */
    fun onCreateView(view: View): View {
        logD(TAG, "BaseFragment-->onCreateView()")
        rootView = view
        loading = view.findViewById(R.id.loading)
        return view
    }

    /**
     * 开始加载，将加载等待控件显示。
     */
    @CallSuper
    override fun startLoading() {
        loading?.visibility = View.VISIBLE
        hideLoadErrorView()
    }

    /**
     * 加载完成，将加载等待控件隐藏。
     */
    @CallSuper
    override fun loadFinished() {
        loading?.visibility = View.GONE
    }

    /**
     * 加载失败，将加载等待控件隐藏。
     */
    override fun loadFailed(msg: String?) {
        loading?.visibility = View.GONE
    }

    /**
     * 当Fragment中的加载内容服务器返回失败或网络异常，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     * @param block 点击屏幕重新加载，回调处理。
     */
    protected fun showLoadErrorView(tip: String, block: View.() -> Unit) {
        if (loadErrorView != null) {
            loadErrorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
            val viewStub = rootView?.findViewById<ViewStub>(R.id.loadErrorView)
            if (viewStub != null) {
                loadErrorView = viewStub.inflate()
                val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
                loadErrorText?.text = tip
                val loadErrorRootView = loadErrorView?.findViewById<View>(R.id.loadErrorRootView)
                loadErrorRootView?.setOnClickListener {
                    it?.block()
                }
            }
        }
    }

    /**
     * 将load error view进行隐藏。
     */
    protected fun hideLoadErrorView() {
        loadErrorView?.visibility = View.GONE
    }
}