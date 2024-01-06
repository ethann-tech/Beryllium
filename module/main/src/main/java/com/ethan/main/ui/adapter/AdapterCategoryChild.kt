package com.ethan.main.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanCategorySub
import com.ethan.main.R
import com.ethan.main.databinding.ItemCategoryChildListBinding
import io.github.uhsk.kit.android.dp2px

class AdapterCategoryChild : BaseQuickAdapter<BeanCategorySub, DataBindingHolder<ItemCategoryChildListBinding>>() {
    override fun onBindViewHolder(holder: DataBindingHolder<ItemCategoryChildListBinding>, position: Int, item: BeanCategorySub?) {
        val mBinding: ItemCategoryChildListBinding = holder.binding
        item?.let {
            mBinding.tvCategoryName.text = it.title
        }
        val params = mBinding.root.layoutParams as MarginLayoutParams
        if (position % 2 == 1) {
            params.marginStart = 4.dp2px()
        } else {
            params.marginEnd = 4.dp2px()
        }
        mBinding.root.layoutParams = params
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemCategoryChildListBinding> {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_category_child_list, parent, false)
        return DataBindingHolder<ItemCategoryChildListBinding>(rootView)
    }
}