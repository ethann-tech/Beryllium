package com.ethan.main.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterNavMain constructor(private val mFragments:List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle){

    override fun getItemCount(): Int =mFragments.size

    override fun createFragment(position: Int): Fragment =mFragments[position]

}

