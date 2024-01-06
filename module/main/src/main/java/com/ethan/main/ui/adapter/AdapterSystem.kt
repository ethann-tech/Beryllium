package com.ethan.main.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanSystem
import com.ethan.main.R
import com.ethan.main.databinding.ItemSystemViewBinding

class AdapterSystem : BaseQuickAdapter<BeanSystem, DataBindingHolder<ItemSystemViewBinding>>() {
    override fun onBindViewHolder(holder: DataBindingHolder<ItemSystemViewBinding>, position: Int, item: BeanSystem?) {
        val mBinding = holder.binding
        mBinding.tvTitle.text = item?.name
        mBinding.tvSubTitle.text = item?.children?.map { it.name }.toString().replace(oldValue = ",", newValue = " ")
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemSystemViewBinding> {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_system_view, parent, false)
        return DataBindingHolder(rootView)
    }
}