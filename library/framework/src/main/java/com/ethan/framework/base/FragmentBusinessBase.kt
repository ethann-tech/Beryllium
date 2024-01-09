package com.ethan.framework.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.ethan.framework.util.LoadingUtil
import com.ethan.zincum.base.FragmentDataBindingBase
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class FragmentBusinessBase<DB : ViewDataBinding> : FragmentDataBindingBase<DB>(),KoinComponent {
    private  val mLoadingUtil: LoadingUtil by lazy { LoadingUtil(mContext) }
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    protected val mGson: Gson by inject<Gson>()

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun showLoading(message: String) {
        mLoadingUtil.showLoading(text = message)
    }
    override fun hideLoading() {
        super.hideLoading()
        mLoadingUtil.hideLoading()
    }

}