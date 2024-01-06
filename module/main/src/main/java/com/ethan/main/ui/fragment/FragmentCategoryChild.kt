package com.ethan.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.ethan.common.bean.BeanCategorySub
import com.ethan.common.constant.RouterPath
import com.ethan.main.R
import com.ethan.main.databinding.FragmentCategoryChildBinding
import com.ethan.main.ui.adapter.AdapterCategoryChild
import com.ethan.zincum.base.FragmentDataBindingBase
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import zlc.season.butterfly.Butterfly

class FragmentCategoryChild : FragmentDataBindingBase<FragmentCategoryChildBinding>() {
    private lateinit var mList: List<BeanCategorySub>
    private val mGson by inject<Gson>()
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private val mAdapter by lazy { AdapterCategoryChild() }
    fun newInstance(list: List<BeanCategorySub>): FragmentCategoryChild {
        val fragment = FragmentCategoryChild()
        fragment.mList = list
        return fragment
    }


    override fun getLayoutResId(): Int = R.layout.fragment_category_child

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding.recycler.apply {
            adapter = mAdapter
        }
        mLogger.info("LOG:FragmentCategoryChild:initView mList={}", mGson.toJson(mList))
        mAdapter.submitList(list = mList)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item: BeanCategorySub? =mAdapter.getItem(position)
            Butterfly.agile(scheme = RouterPath.ACTIVITY_ARTICLE_DETAIL).params(Pair("title", item?.title), Pair("url", item?.link)).carry(mContext, onError = {
                mLogger.info("LOG:FragmentCategoryChild:initView onError")
            }, onSuccess = {
                mLogger.info("LOG:FragmentCategoryChild:initView onSuccess")
            })
        }
    }


}