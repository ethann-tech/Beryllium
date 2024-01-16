package com.ethan.main.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanVideoItem
import com.ethan.main.R
import com.ethan.main.databinding.ItemHomeVideoBinding

class AdapterVideoItem :BaseQuickAdapter<BeanVideoItem,DataBindingHolder<ItemHomeVideoBinding>>() {
    override fun onBindViewHolder(holder: DataBindingHolder<ItemHomeVideoBinding>, position: Int, item: BeanVideoItem?) {
        val mBinding =holder.binding
        mBinding.tvTitle.text = item?.title
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemHomeVideoBinding> {
        val rootView =LayoutInflater.from(context).inflate(R.layout.item_home_video,parent,false)
        return DataBindingHolder(rootView)
    }
}