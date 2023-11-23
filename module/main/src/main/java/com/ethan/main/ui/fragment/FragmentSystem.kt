package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentSystemBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * fragment 体系
 */
class FragmentSystem : FragmentBusinessBase<FragmentSystemBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_system
    override fun initData() {
        super.initData()

    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }
}