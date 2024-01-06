package com.ethan.main.ui.fragment

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.FragmentCategoryBinding
import com.ethan.main.ui.adapter.AdapterParentCategory
import com.ethan.main.ui.adapter.AdapterViewPage2Fragment
import com.ethan.main.ui.viewmodel.ViewModelCategory
import com.hjq.toast.Toaster
import org.koin.core.component.inject

class FragmentCategory : FragmentBusinessBase<FragmentCategoryBinding>() {
    private val mAdapterParent: AdapterParentCategory by lazy { AdapterParentCategory() }
    private val mViewModel by inject<ViewModelCategory>()
    private lateinit var mAdapterChildFragment: AdapterViewPage2Fragment
    private var mFragmentList: SparseArray<Fragment> = SparseArray<Fragment>()
    override fun getLayoutResId(): Int = R.layout.fragment_category


    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mBinding.recycler.apply { adapter = mAdapterParent }

        mAdapterParent.setOnItemClickListener { adapter, view, position ->
            mAdapterParent.mCurrentPosition = position
            mBinding.viewPager.currentItem = position
        }

        mAdapterChildFragment = AdapterViewPage2Fragment(childFragmentManager, lifecycle = lifecycle, mFragments = mFragmentList)
        mBinding.viewPager.apply {
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = mAdapterChildFragment
        }
        mBinding.viewPager.registerOnPageChangeCallback(mViewPager2Callback)
    }

    private val mViewPager2Callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            mBinding.recycler.smoothScrollToPosition(position)
            mAdapterParent.mCurrentPosition =position
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.requestCategoryList()
        mViewModel.mListCategory.observe(this) {
            it.forEachIndexed { index, item ->
                mFragmentList.put(index, FragmentCategoryChild().newInstance(item.articles))
            }
            mAdapterParent.submitList(it)
            mAdapterChildFragment.notifyItemRangeChanged(0, mFragmentList.size())
        }
        mViewModel.mErrorMsgLiveData.observe(this) {
            Toaster.show(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.viewPager.unregisterOnPageChangeCallback(mViewPager2Callback)
    }


}