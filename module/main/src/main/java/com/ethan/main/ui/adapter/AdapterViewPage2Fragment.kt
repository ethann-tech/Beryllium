package com.ethan.main.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPage2Fragment(fm: FragmentManager, lifecycle: Lifecycle, private var mFragments: SparseArray<Fragment>) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = mFragments.size()

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }

    fun setFragments(mFragments: SparseArray<Fragment>) {
        this.mFragments = mFragments
    }
}