package com.ethan.main.ui.activity

import androidx.lifecycle.lifecycleScope
import com.ethan.common.constant.AppConstant
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityBusinessBase
import com.ethan.framework.ext.countDownCoroutines
import com.ethan.main.R
import com.ethan.main.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.uhsk.kit.android.dataStore
import io.github.uhsk.kit.jetpack.datastore.getString
import kotlinx.coroutines.launch
import zlc.season.butterfly.Butterfly
import zlc.season.butterfly.annotation.Agile

@AndroidEntryPoint
@Agile(scheme = RouterPath.ACTIVITY_MAIN_SPLASH)
class ActivitySplash : ActivityBusinessBase<ActivitySplashBinding>() {

    override fun layoutResId(): Int = R.layout.activity_splash
    override fun initWindowAttributes() {}

    override fun initView() {

        countDownCoroutines(total = 3, scope = lifecycleScope, onTick = {
            mBinding.tvSkip.text = "跳过".plus(it)
        }, onStart = {
            mLogger.info("LOG:ActivitySplash:initView onStart")
        }, onFinish = {
            mLogger.info("LOG:ActivitySplash:initView onFinish")
            lifecycleScope.launch {
                val beanLoginStr =mContext.dataStore.getString(AppConstant.USER_INFO)
                if (beanLoginStr.isEmpty()){
                    Butterfly.agile(RouterPath.ACTIVITY_VERIFY_LOGIN).carry(mContext)
                }else{
                    Butterfly.agile(RouterPath.ACTIVITY_MAIN_HOME).carry(mContext)
                }
            }
            finish()
        })
    }
    override fun initListener() { }

    override fun bindLayoutView() {
        super.bindLayoutView()
        mBinding.tvSkip.text = "跳过3s"
    }
}