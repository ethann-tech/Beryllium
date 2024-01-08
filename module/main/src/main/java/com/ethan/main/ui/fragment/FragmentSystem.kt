package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.util.setOnDebouncedItemClick
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentSystemBinding
import com.ethan.main.ui.adapter.AdapterSystem
import com.ethan.main.ui.viewmodel.ViewModelSystem
import com.hjq.toast.Toaster
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import io.github.uhsk.kit.asColor
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
        UltimateBarX.statusBarOnly(this).color(com.ethan.common.R.color.white.asColor(mContext)).light(true).apply()
        mBinding.recycler.apply { adapter = mAdapter }
        initListener()
    }

    private fun initListener() {
        mAdapter.setOnDebouncedItemClick { adapter, view, position ->
            Toaster.show("点击了：${mAdapter.getItem(position)?.name}")
        }
    }
}