package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.QuickAdapterHelper
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentMineBinding
import com.ethan.main.ui.adapter.AdapterMineHeader
import com.ethan.main.ui.adapter.AdapterArticleList
import com.ethan.main.ui.viewmodel.ViewModelMine
import org.koin.core.component.inject

/**
 * fragment 我的
 */
class FragmentMine : FragmentBusinessBase<FragmentMineBinding>() {
    private val mViewModel: ViewModelMine by inject<ViewModelMine>()


    private val mAdapter: AdapterArticleList by lazy { AdapterArticleList() }

    private val mHelper: QuickAdapterHelper by lazy { QuickAdapterHelper.Builder(mAdapter).build() }

    override fun getLayoutResId(): Int = R.layout.fragment_mine


    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState) //        mViewModel.requestRecommendList(page = mViewModel.page)
        mBinding.recycler.adapter = mHelper.adapter
        mHelper.addBeforeAdapter(0, AdapterMineHeader())
        mBinding.layoutSmartRefresh.autoRefresh()
    }

    override fun initData() {
        super.initData()
        updateData()
        initListener()
    }

    private fun updateData() {
        mViewModel.mRecommendListData.observe(this) {
            mAdapter.addAll(it)
        }
    }

    private fun initListener() {
        mBinding.layoutSmartRefresh.setOnRefreshListener {
            mViewModel.page = 0
            mViewModel.requestRecommendList(mViewModel.page)
            mBinding.layoutSmartRefresh.finishRefresh(1000)
        }
        mBinding.layoutSmartRefresh.setOnLoadMoreListener {
            mViewModel.page++
            mViewModel.requestRecommendList(mViewModel.page)
            mBinding.layoutSmartRefresh.finishLoadMore(1000)
        }
    }
}