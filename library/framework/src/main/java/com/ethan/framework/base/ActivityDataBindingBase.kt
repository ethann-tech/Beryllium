package com.ethan.framework.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class ActivityDataBindingBase<DB : ViewDataBinding> : ActivityBase() {
    lateinit var mBinding: DB

    override fun bindLayoutResId(layoutResId: Int) {
        super.bindLayoutResId(layoutResId)
        mBinding = DataBindingUtil.setContentView(this, layoutResId())
    }

}