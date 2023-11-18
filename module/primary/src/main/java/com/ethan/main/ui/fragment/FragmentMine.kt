package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentMineBinding

/**
 * fragment 体系
 */
class FragmentMine : FragmentBusinessBase<FragmentMineBinding>() {
    override fun getLayoutResId(): Int= R.layout.fragment_mine
    override fun initData() {
        super.initData()

    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }
}