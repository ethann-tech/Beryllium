package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.framework.decoration.StaggeredItemDecoration
import com.ethan.main.R
import com.ethan.main.databinding.FragmentHomeTabBinding
import com.ethan.main.ui.adapter.AdapterHomeTab
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import io.github.uhsk.kit.android.dp2px
import org.koin.android.ext.android.inject

class FragmentHomeTab private constructor() : FragmentBusinessBase<FragmentHomeTabBinding>() {
    private lateinit var mBeanCategory: BeanProjectCategory
    private val mViewModel by inject<ViewModelHome>()
    private val mAdapter by lazy { AdapterHomeTab() }

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
        mBinding.recycler.apply {
            adapter = mAdapter
            addItemDecoration(StaggeredItemDecoration(16.dp2px()))
        }
        mBinding.layoutSwipeRefresh.autoRefresh()

        mBinding.layoutSwipeRefresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mViewModel.page = 0
                mViewModel.requestArticleListByCid(mBeanCategory.id)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mViewModel.page++
                mViewModel.requestArticleListByCid(mBeanCategory.id)
            }
        })
    }

    override fun initData() {
        super.initData()
        mViewModel.mArticleList.observe(this) {
            if (mBinding.layoutSwipeRefresh.isLoading) {
                mBinding.layoutSwipeRefresh.finishLoadMore(300)
            }
            if (mBinding.layoutSwipeRefresh.isRefreshing) {
                mBinding.layoutSwipeRefresh.finishRefresh(300)
            }
            mAdapter.addAll(it)
        }

    }
}