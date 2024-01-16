package com.ethan.main.ui.fragment

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.framework.util.ToastUtil
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeBinding
import com.ethan.main.ui.adapter.AdapterHomeBanner
import com.ethan.main.ui.adapter.AdapterHomeFragmentTab
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hjq.toast.Toaster
import com.youth.banner.indicator.CircleIndicator
import org.koin.core.component.inject

class FragmentHome private constructor() : FragmentBusinessBase<FragmentHomeBinding>() {
    private val mViewModel: ViewModelHome by inject<ViewModelHome>()
    private val mAdapterBanner: AdapterHomeBanner by lazy { AdapterHomeBanner(mutableListOf()) }
    private var mChildFragment = SparseArray<Fragment>()
    private var mProjectTabs: MutableList<BeanProjectCategory> = mutableListOf()
    private lateinit var mediator: TabLayoutMediator
    private val mAdapterFragmentTab by lazy { AdapterHomeFragmentTab(mChildFragment, childFragmentManager, lifecycle = lifecycle) }

    companion object {
        fun newInstance() = FragmentHome()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home


    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding.banner.addBannerLifecycleObserver(viewLifecycleOwner).setAdapter(mAdapterBanner).indicator = CircleIndicator(context)
        showLoading("加载中...")
        initTab()
    }

    override fun initData() {
        super.initData()
        mViewModel.requestHomeBanner()
        mViewModel.mBannerListData.observe(viewLifecycleOwner) { mAdapterBanner.setDatas(it) }
        mBinding.banner.setOnBannerListener { data, position ->
            Toaster.show("$position")
        }
        mViewModel.mErrorMsgLiveData.observe(this) {
            if (it.isNotBlank()) {
                ToastUtil.showToast(it)
            } else {
                ToastUtil.showToast("项目列表获取错误")
            }
        }

        mViewModel.mProjectCategoryList.observe(this) {

            mBinding.tabLayout.apply {
                mProjectTabs = mProjectTabs.filter { it.name == "短视频" }.toMutableList()
                it.forEachIndexed { index, item ->
                    mProjectTabs.add(item)
                    mChildFragment[index + 1] = FragmentHomeTab.newInstance(item)
                }
                mAdapterFragmentTab.setData(fragments = mChildFragment)
                mBinding.tabLayout.let {
                    it.post { it.getTabAt(0)?.select() }
                }
            }
            hideLoading()
        }
        initListener()
    }


    private fun initTab() {
        mBinding.tabViewPage.apply {
            adapter = mAdapterFragmentTab
            isUserInputEnabled =true
            offscreenPageLimit =1
        }
        mChildFragment[0] = FragmentVideo.newInstance()
        mProjectTabs.add(0, BeanProjectCategory(id = 0, name = "短视频"))
        mediator = TabLayoutMediator(mBinding.tabLayout, mBinding.tabViewPage) { tab, position ->
            tab.text = mProjectTabs[position].name
        }
        mediator.attach()
    }

    private fun initListener() {
        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mBinding.tabViewPage.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }

    override fun onStart() {
        super.onStart()
        mViewModel.requestProjectCategoryList()
    }
}