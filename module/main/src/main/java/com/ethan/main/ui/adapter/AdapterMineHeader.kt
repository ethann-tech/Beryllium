package com.ethan.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseSingleItemAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.main.R
import com.ethan.main.databinding.FragmentMineHeaderBinding

class AdapterMineHeader :BaseSingleItemAdapter<Any,DataBindingHolder<FragmentMineHeaderBinding>>() {
    override fun onBindViewHolder(holder: DataBindingHolder<FragmentMineHeaderBinding>, item: Any?) {

    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<FragmentMineHeaderBinding> {
       return DataBindingHolder<FragmentMineHeaderBinding>(R.layout.fragment_mine_header, parent)
    }

}