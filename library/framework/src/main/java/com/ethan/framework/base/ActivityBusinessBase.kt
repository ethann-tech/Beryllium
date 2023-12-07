package com.ethan.framework.base


import androidx.core.view.WindowCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ethan.framework.util.LoadingUtil
import com.ethan.zincum.base.ActivityDataBindingBase
import org.koin.core.component.KoinComponent

abstract class ActivityBusinessBase<DB : ViewDataBinding> : ActivityDataBindingBase<DB>() ,KoinComponent{
    private lateinit var mLoadingUtil: LoadingUtil
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun initWindowAttributes() {
        val controller =WindowCompat.getInsetsController(window,window.decorView)

        WindowCompat.setDecorFitsSystemWindows(window,false)

    }
    override fun initView() {
        mLoadingUtil =LoadingUtil(mContext)
    }
    override fun showLoading(message: String) {
        super.showLoading(message)
        mLoadingUtil.showLoading(text = message)
    }
    override fun hideLoading() {
        super.hideLoading()
        mLoadingUtil.hideLoading()
    }

    protected fun replaceFragment(id:Int,fragment:Fragment){
        val ft =supportFragmentManager.beginTransaction()
        ft.replace(id,fragment)
        ft.commitNow()
    }

}