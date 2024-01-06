package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentSystemBinding
import com.ethan.main.ui.adapter.AdapterSystem
import com.ethan.main.ui.viewmodel.ViewModelSystem
import org.koin.android.ext.android.inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * fragment 体系
 */
class FragmentSystem : FragmentBusinessBase<FragmentSystemBinding>() {

    private val mViewModel by inject<ViewModelSystem>()
    private val mAdapter by lazy { AdapterSystem() }

    override fun getLayoutResId(): Int = R.layout.fragment_system
    override fun initData() {
        super.initData()
        mViewModel.requestSystemList()
        mViewModel.mSystemList.observe(this) {
            mAdapter.submitList(list = it)
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mBinding.recycler.apply {
            adapter = mAdapter
        }


    }
}