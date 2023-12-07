package com.ethan.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.ethan.framework.util.LoadingUtil
import com.ethan.zincum.base.FragmentDataBindingBase
import org.koin.core.component.KoinComponent

abstract class FragmentBusinessBase<DB : ViewDataBinding> : FragmentDataBindingBase<DB>(),KoinComponent {
    private lateinit var mLoadingUtil: LoadingUtil
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mLoadingUtil = LoadingUtil(mContext)
    }

    override fun showLoading(message: String) {
        mLoadingUtil.showLoading(text = message)
    }
    override fun hideLoading() {
        super.hideLoading()
        mLoadingUtil.hideLoading()
    }

}