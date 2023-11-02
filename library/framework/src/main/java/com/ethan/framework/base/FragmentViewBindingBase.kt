package com.ethan.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

open class FragmentViewBindingBase<VB : ViewBinding>(private val inflater: (LayoutInflater) -> VB) : FragmentBase() {
    lateinit var mBinding: VB

    override fun getLayoutView(inflater1: LayoutInflater, container: ViewGroup?): View {
        return inflater(inflater1).root
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }
}