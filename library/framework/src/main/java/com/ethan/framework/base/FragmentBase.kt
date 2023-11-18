package com.ethan.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ethan.framework.util.LoadingUtil

abstract class FragmentBase : Fragment() {

    private val mLoadingUtil by lazy { LoadingUtil(requireContext()) }
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)


    protected var mIsViewCreate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContainerView(inflater = inflater, container = container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view = view, savedInstanceState = savedInstanceState)
        initData()
    }

    private fun getContainerView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return if (getLayoutResId() == -1) {
            getLayoutView(inflater = inflater, container = container)
        } else {
            inflater.inflate(getLayoutResId(), container, false)
        }
    }

    open fun getLayoutResId(): Int = -1

    open fun getLayoutView(inflater: LayoutInflater,container: ViewGroup?): View? = null

    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(view: View, savedInstanceState: Bundle?)

    open fun initData() {}
    fun showLoading(message: String) {
        mLoadingUtil.showLoading(text = message)
    }

    fun hideLoading() {
        mLoadingUtil.hideLoading()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (mIsViewCreate) {
            onFragmentVisible(isVisibleToUser)
        }
    }

    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            onFragmentVisible(true)
        }
    }

    override fun onStop() {
        super.onStop()
        if (userVisibleHint) {
            onFragmentVisible(false)
        }
    }

    open fun onFragmentVisible(isVisibleToUser: Boolean) {
        mLogger.info("LOG:ActivityBase:onFragmentVisible isVisibleToUser={}", isVisibleToUser)
    }


}