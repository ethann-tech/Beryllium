package com.ethan.main.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethan.framework.base.FragmentViewBindingBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentSystemBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * fragment 体系
 */
class FragmentSystem : FragmentViewBindingBase<FragmentSystemBinding>(FragmentSystemBinding::inflate) {

    override fun initData() {
        super.initData()

    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }
}