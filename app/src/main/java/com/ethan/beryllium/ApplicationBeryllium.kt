package com.ethan.beryllium

import android.content.Context
import android.view.Gravity
import androidx.multidex.MultiDex
import com.ethan.beryllium.koin.allModule
import com.ethan.framework.ApplicationLifecycle
import com.ethan.framework.log.ImplLoggerManager
import com.hjq.toast.ToastStrategy
import com.hjq.toast.Toaster
import dagger.hilt.android.HiltAndroidApp
import io.github.uhsk.kit.format
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.Date
import java.util.logging.Level
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
        initToaster()
        initKoin()
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
}