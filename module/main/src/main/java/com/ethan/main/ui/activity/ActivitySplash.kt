package com.ethan.main.ui.activity

import androidx.lifecycle.lifecycleScope
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityBusinessBase
import com.ethan.framework.ext.countDownCoroutines
import com.ethan.main.R
import com.ethan.main.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import zlc.season.butterfly.Butterfly
import zlc.season.butterfly.annotation.Agile

@AndroidEntryPoint
@Agile(scheme = RouterPath.ACTIVITY_MAIN_SPLASH)
class ActivitySplash : ActivityBusinessBase<ActivitySplashBinding>() {

    override fun layoutResId(): Int = R.layout.activity_splash
    override fun initWindowAttributes() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.decorView.windowInsetsController?.hide(android.view.WindowInsets.Type.systemBars())
//        }
    }

    override fun initView() {

        countDownCoroutines(total = 3, scope = lifecycleScope, onTick = {
            mBinding.tvSkip.text = "跳过$it"
        }, onStart = {
            mLogger.info("LOG:ActivitySplash:initView onStart")
        }, onFinish = {
            mLogger.info("LOG:ActivitySplash:initView onFinish")
//            Butterfly.agile(RouterPath.MAIN_ACTIVITY_HOME).carry(mContext)
            Butterfly.agile(RouterPath.ACTIVITY_VERIFY_LOGIN).carry(mContext)
            finish()
        })
    }


    override fun initListener() {

    }

    override fun bindLayoutView() {
        super.bindLayoutView()
        mBinding.tvSkip.text = "跳过3s"
    }
}