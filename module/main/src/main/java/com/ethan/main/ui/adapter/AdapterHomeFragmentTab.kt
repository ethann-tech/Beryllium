package com.ethan.main.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterHomeFragmentTab(private var mFragments: SparseArray<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = mFragments.size()

    override fun createFragment(position: Int): Fragment = mFragments[position]
    fun setData(fragments: SparseArray<Fragment>){
        this.mFragments =fragments
        notifyDataSetChanged()
    }
}