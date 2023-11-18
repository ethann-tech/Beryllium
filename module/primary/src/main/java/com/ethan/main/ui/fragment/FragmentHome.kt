package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeBinding
import com.ethan.main.ui.adapter.AdapterHomeBanner
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.hjq.toast.Toaster
import com.youth.banner.indicator.CircleIndicator

class FragmentHome private constructor() : FragmentBusinessBase<FragmentHomeBinding>() {


    private val mAdapterBanner:AdapterHomeBanner by lazy {
        AdapterHomeBanner(mutableListOf())
    }
    companion object {
        fun newInstance() = FragmentHome()
    }

    private lateinit var viewModel: ViewModelHome

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding.banner.addBannerLifecycleObserver(viewLifecycleOwner)
            .setAdapter(mAdapterBanner)
            .indicator = CircleIndicator(context)
    }

    override fun initData() {
        super.initData()
        mLogger.info("LOG:FragmentHome:initData")
        viewModel = ViewModelHome()
        viewModel.requestHomeBanner()
        viewModel.mBannerListData.observe(viewLifecycleOwner) {
          mAdapterBanner.setDatas(it)
        }
        mBinding.banner.setOnBannerListener { data, position ->
            Toaster.show("$position")
             }
    }



}