package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.framework.base.FragmentBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeBinding
import com.ethan.main.ui.viewmodel.ViewModelFragmentHome

class FragmentHome private constructor() : FragmentBase() {

    private lateinit var mBinding: FragmentHomeBinding

    companion object {
        fun newInstance() = FragmentHome()
    }

    private lateinit var viewModel: ViewModelFragmentHome

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentHomeBinding.bind(view)
        //mBinding.includeCommonToolbar.toolbar.apply {
        //    title ="首页"
        //}
    }

    override fun initData() {
        super.initData()
    }

}