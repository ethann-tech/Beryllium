package com.ethan.main.ui.activity

import com.angcyo.tablayout.delegate2.ViewPager2Delegate
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityViewBindingBase
import com.ethan.main.databinding.ActivityMainBinding
import com.ethan.main.ui.adapter.AdapterNavMain
import com.ethan.main.ui.fragment.FragmentCategory
import com.ethan.main.ui.fragment.FragmentHome
import com.ethan.main.ui.fragment.FragmentMine
import com.ethan.main.ui.fragment.FragmentSystem
import dagger.hilt.android.AndroidEntryPoint
import zlc.season.butterfly.annotation.Agile

@AndroidEntryPoint
@Agile(scheme = RouterPath.MAIN_ACTIVITY_HOME)
class ActivityMain : ActivityViewBindingBase<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mFragments = arrayListOf(FragmentHome(), FragmentCategory(), FragmentSystem(), FragmentMine())
    private val mAdapter: AdapterNavMain by lazy { AdapterNavMain(mFragments = mFragments, fm = supportFragmentManager, lifecycle = lifecycle) }
    override fun initWindowAttributes() {
    }

    override fun initView() {
        mBinding.viewpage.adapter = mAdapter
        ViewPager2Delegate.install(mBinding.viewpage, mBinding.navMain)
    }

    override fun initListener() {
        mBinding.navMain.observeIndexChange { fromIndex, toIndex, reselect, fromUser ->
            mBinding.navMain._viewPagerDelegate?.onSetCurrentItem(fromIndex, toIndex, reselect = reselect, fromUser = fromUser)
        }
    }

}