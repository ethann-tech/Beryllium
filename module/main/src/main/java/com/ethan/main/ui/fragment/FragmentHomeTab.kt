package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeTabBinding

class FragmentHomeTab private constructor():FragmentBusinessBase<FragmentHomeTabBinding>() {
    private lateinit var mBeanCategory: BeanProjectCategory

    companion object {
        fun newInstance(mBeanCategory: BeanProjectCategory): FragmentHomeTab {
            val mInstance = FragmentHomeTab()
            mInstance.mBeanCategory = mBeanCategory
            return mInstance
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home_tab

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mBinding.tv.text =mGson.toJson(mBeanCategory)
    }
}