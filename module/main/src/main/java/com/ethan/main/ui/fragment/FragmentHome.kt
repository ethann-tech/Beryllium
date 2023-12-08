package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.framework.util.ToastUtil
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeBinding
import com.ethan.main.ui.adapter.AdapterFragmentTab
import com.ethan.main.ui.adapter.AdapterHomeBanner
import com.ethan.main.ui.adapter.AdapterNavMain
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hjq.toast.Toaster
import com.youth.banner.indicator.CircleIndicator
import io.github.uhsk.kit.libs.codec.binary.StringUtils
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject

class FragmentHome private constructor() : FragmentBusinessBase<FragmentHomeBinding>() {
    private val mViewModel: ViewModelHome by inject<ViewModelHome>()
    private val mAdapterBanner: AdapterHomeBanner by lazy { AdapterHomeBanner(mutableListOf()) }


    private  var mChildFragment = hashMapOf<String,Fragment>();
    companion object {
        fun newInstance() = FragmentHome()
    }


    override fun getLayoutResId(): Int = R.layout.fragment_home


    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding.banner.addBannerLifecycleObserver(viewLifecycleOwner).setAdapter(mAdapterBanner).indicator = CircleIndicator(context)
    }

    override fun initData() {
        super.initData()
        mLogger.info("LOG:FragmentHome:initData")
        mViewModel.requestHomeBanner()
//        initListener()
        mViewModel.mBannerListData.observe(viewLifecycleOwner) {
            mAdapterBanner.setDatas(it)
        }
        mBinding.banner.setOnBannerListener { data, position ->
            Toaster.show("$position")
        }

        mViewModel.mErrorMsgLiveData.observe(this){
            if (it.isNotBlank()){
                ToastUtil.showToast(it)
            }else{
                ToastUtil.showToast("项目列表获取错误")
            }

        }
        mViewModel.mProjectCategoryList.observe(this){
           mBinding.tabLayout.apply {
             it.forEach {
//                 addTab(this.newTab().setText(it.name))
                 mChildFragment[it.name] = FragmentTab.newInstance(it)
             }
               mBinding.tabViewPage.apply {
                   adapter = AdapterFragmentTab(mChildFragment,childFragmentManager, lifecycle = lifecycle)
               }
               TabLayoutMediator(mBinding.tabLayout,mBinding.tabViewPage,false,true){
                       tab,position->
                   tab.text =mChildFragment.keys.toList()[position]

               }.attach()
           }
        }

    }
    private fun initListener(){
        mBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                mBinding.tabViewPage.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

    }



    override fun onStart() {
        super.onStart()
        mViewModel.requestProjectCategoryList()
    }


}