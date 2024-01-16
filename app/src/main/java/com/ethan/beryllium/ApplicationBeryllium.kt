package com.ethan.beryllium

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.multidex.MultiDex
import com.ethan.beryllium.koin.allModule
import com.ethan.framework.ApplicationLifecycle
import com.ethan.framework.log.ImplLoggerManager
import com.ethan.framework.manager.ActivityManager
import com.ethan.framework.manager.AppFrontBackListener
import com.ethan.framework.manager.AppFrontBackManager
import com.hjq.toast.ToastStrategy
import com.hjq.toast.Toaster
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dagger.hilt.android.HiltAndroidApp
import io.github.uhsk.kit.format
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.Date
import kotlin.coroutines.coroutineContext

/**
 * ethan
 */
@HiltAndroidApp
class ApplicationBeryllium : ApplicationLifecycle() {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private val mLoggerManager by lazy { ImplLoggerManager(mContext = this.baseContext) }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override suspend fun onCreatedBySuspend() {
        super.onCreatedBySuspend()
        initLogger()
        appFrontBackRegister()
        registerActivityLifecycle()
        initToaster()
        initKoin()
        initSmartRefreshLayout()
    }

    private suspend fun initLogger() {
        mLoggerManager.init()
        mLogger.debug("LOG:ApplicationBeryllium:onCreate 1={}", 1)
        mLogger.debug("LOG:ApplicationBeryllium:onCreate coroutineContext={}", coroutineContext)
        mLogger.error("LOG:ApplicationBeryllium:onCreate =============================================================")
        mLogger.debug("LOG:ApplicationBeryllium:onCreate content=debug !!! this log should remove !!!")
        mLogger.trace("LOG:ApplicationBeryllium:onCreate content=trace !!! this log should remove !!!")
        mLogger.info("LOG:ApplicationBeryllium:onCreate content=info  !!! this log should remove !!!")
        mLogger.warn("LOG:ApplicationBeryllium:onCreate content=warn  !!! this log should remove !!!")
        mLogger.error("LOG:ApplicationBeryllium:onCreate content=error !!! this log should remove !!!")
        mLogger.info("LOG:ApplicationBeryllium:onCreate apk build time={}", Date().format())
        mLogger.error("LOG:ApplicationBeryllium:onCreate =============================================================")
    }

    private fun initToaster() {
        Toaster.init(this)
        Toaster.setGravity(Gravity.TOP)
        Toaster.setStrategy(ToastStrategy(ToastStrategy.SHOW_STRATEGY_TYPE_QUEUE))

    }

    private fun initKoin() {
        startKoin {
            androidLogger(org.koin.core.logger.Level.INFO)
            androidContext(this@ApplicationBeryllium)
            modules(allModule)
        }
    }

    private fun initSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ -> ClassicsHeader(context) }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context).setDrawableSize(20f) }
    }


    private fun appFrontBackRegister() {
        AppFrontBackManager.register(this, object : AppFrontBackListener {
            override fun onBackground(activity: Activity?) {
                mLogger.info("LOG:ApplicationBeryllium:onBackground ={} running onBackground", activity?.javaClass?.simpleName)
            }

            override fun onFront(activity: Activity?) {
                mLogger.info("LOG:ApplicationBeryllium:onFront ={} running onFront", activity?.javaClass?.simpleName)
            }
        })
    }

    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.pop(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                ActivityManager.push(activity)
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }
}