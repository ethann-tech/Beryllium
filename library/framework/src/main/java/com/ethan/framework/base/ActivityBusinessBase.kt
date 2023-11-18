package com.ethan.framework.base


import androidx.databinding.ViewDataBinding
import com.ethan.framework.util.LoadingUtil
import com.ethan.zincum.base.ActivityDataBindingBase

abstract class ActivityBusinessBase<DB : ViewDataBinding> : ActivityDataBindingBase<DB>() {
    private lateinit var mLoadingUtil: LoadingUtil
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun initView() {
        mLoadingUtil =LoadingUtil(mContext)
    }
    override fun showLoading(message: String) {
        super.showLoading(message)
        mLoadingUtil.showLoading(text = message)
    }
    override fun hideLoading() {
        super.hideLoading()
        mLoadingUtil.hideLoading()
    }


}