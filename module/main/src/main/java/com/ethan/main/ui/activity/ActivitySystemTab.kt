package com.ethan.main.ui.activity

import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ethan.common.bean.BeanSystem
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityBusinessBase
import com.ethan.main.R
import com.ethan.main.databinding.ActivitySystemTabBinding
import com.ethan.main.ui.adapter.AdapterFragmentTab
import com.ethan.main.ui.fragment.FragmentSystemTab
import com.google.android.material.tabs.TabLayoutMediator
import com.hjq.toast.Toaster
import io.github.uhsk.kit.android.setNavigationNoDoubleClickListener
import io.github.uhsk.kit.asDrawable
import zlc.season.butterfly.annotation.Agile

@Agile(scheme = RouterPath.ACTIVITY_SYSTEM_TAB)
class ActivitySystemTab : ActivityBusinessBase<ActivitySystemTabBinding>() {
    private var mFragmentTabs = hashMapOf<String, Fragment>()

    private val mBeanSystem: BeanSystem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("beanSystem", BeanSystem::class.java)
        } else {
            intent.getSerializableExtra("beanSystem") as BeanSystem
        }
    }
    private val mAdapter by lazy { AdapterFragmentTab(mFragments = mFragmentTabs, fm = supportFragmentManager, lifecycle = lifecycle) }

    override fun layoutResId(): Int = R.layout.activity_system_tab
    override fun initView() {
        super.initView()
        mBinding.includeToolbar.tvToolbarTitle.text = mBeanSystem?.name
        setSupportActionBar(mBinding.includeToolbar.toolbar)
        mBinding.includeToolbar.toolbar.navigationIcon = com.ethan.common.R.drawable.nav_back.asDrawable(mContext)
        mBinding.includeToolbar.toolbar.setNavigationNoDoubleClickListener { finish() }
        mBinding.includeToolbar.toolbar.inflateMenu(R.menu.toolbar_menu_system_tab)
        initData()
    }

    private fun initData() {
        mBeanSystem?.children?.forEach {
            mFragmentTabs[it.name] = FragmentSystemTab.newInstance(it)
        }
        mBinding.viewpage2.apply {
            adapter = mAdapter
        }
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewpage2, false, true) { tab, position ->
            tab.text = mFragmentTabs.keys.toList()[position]
        }.attach()

    }

    override fun initListener() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_system_tab, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                Toaster.show("搜索")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}