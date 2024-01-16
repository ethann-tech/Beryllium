package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.common.bean.BeanVideoItem
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.framework.decoration.StaggeredItemDecoration
import com.ethan.main.R
import com.ethan.main.databinding.FragmentVideoBinding
import com.ethan.main.ui.adapter.AdapterVideoItem
import com.ethan.main.ui.viewmodel.ViewModelHome
import io.github.uhsk.kit.android.dp2px
import org.koin.android.ext.android.inject

class FragmentVideo : FragmentBusinessBase<FragmentVideoBinding>() {

    private val mViewModel by inject<ViewModelHome>()

    private val mAdapterVideo by lazy { AdapterVideoItem() }

    companion object {
        fun newInstance(): FragmentVideo {
            val args = Bundle()
            val fragment = FragmentVideo()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_video
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding.recycler.apply {
            addItemDecoration(StaggeredItemDecoration(16.dp2px()))
            adapter = mAdapterVideo
        }
    }

    override fun initData() {
        super.initData()

        for (i in 14 downTo 0) {
            mAdapterVideo.add(BeanVideoItem(id = "123456789", title = "测试数据$i", author = "Ethan", cover = "https://www.wanandroid.com/blogimgs/544/544/o_1111111111111111111111111"))
        }
        mAdapterVideo.notifyItemRangeChanged(0, mAdapterVideo.itemCount)

    }

}