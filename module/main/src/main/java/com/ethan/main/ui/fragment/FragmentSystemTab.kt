package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethan.common.bean.BeanChildSystem
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentSystemTabBinding
import com.ethan.main.ui.adapter.AdapterArticleList
import com.ethan.main.ui.viewmodel.ViewModelSystem
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import org.koin.android.ext.android.inject

class FragmentSystemTab : FragmentBusinessBase<FragmentSystemTabBinding>() {
    private lateinit var mBean: BeanChildSystem

    private val mViewModel by inject<ViewModelSystem>()
    private val mAdapterArticleList by lazy { AdapterArticleList() }

    companion object {
        fun newInstance(bean: BeanChildSystem): FragmentSystemTab {
            val args = Bundle()
            val fragment = FragmentSystemTab()
            fragment.mBean = bean
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mLogger.info("LOG:FragmentSystemTab:initView mBean={}", mGson.toJson(mBean))
        mBinding.recycler.apply {
            layoutManager = LinearLayoutManager(this.context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = mAdapterArticleList
        }
        mBinding.layoutSmartRefresh.autoRefresh()
        initListener()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_system_tab

    override fun initData() {
        super.initData()
        mViewModel.mArticleList.observe(this) {
            mLogger.info("LOG:FragmentSystemTab:initData isLoading={}", mBinding.layoutSmartRefresh.isLoading)
            mLogger.info("LOG:FragmentSystemTab:initData isRefreshing={}", mBinding.layoutSmartRefresh.isRefreshing)
            if (mBinding.layoutSmartRefresh.isLoading){
                mBinding.layoutSmartRefresh.finishLoadMore(300)
            }
            if (mBinding.layoutSmartRefresh.isRefreshing){
                mBinding.layoutSmartRefresh.finishRefresh(300)
            }
            mAdapterArticleList.addAll(it)
        }




    }

    private fun initListener() {
        mBinding.layoutSmartRefresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.finishRefresh(1000)
                mViewModel.page = 0
                mViewModel.requestArticleListByCid(mBean.id)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                refreshLayout.finishRefresh(1000)
                mViewModel.page++
                mViewModel.requestArticleListByCid(mBean.id)
            }
        })
    }
}