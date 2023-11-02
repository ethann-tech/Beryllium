package com.ethan.framework.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class ActivityViewBindingBase<VB : ViewBinding>(private val inflater: (LayoutInflater) -> VB) :
    ActivityBase() {
    lateinit var mBinding: VB
    override fun bindLayoutView() {
        mBinding = inflater(layoutInflater)
        setContentView(mBinding.root)
    }
}