package com.ethan.framework

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.lifecycleScope
import com.ethan.framework.manager.ActivityManager
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

abstract class ApplicationLifecycle : Application(), LifecycleOwner {

    private val mRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    private val mHandle: Handler = Handler(Looper.myLooper()!!)
    private var mLastDispatchRunnable: DispatchRunnable? = null

     companion object{
         private lateinit var mInstance :Application
         fun instance() = mInstance
     }
    open suspend fun onCreatedBySuspend() {
        mInstance =this
    }

    override val lifecycle: Lifecycle
        get() = mRegistry

     override fun onCreate() {
        postDispatchRunnable(Lifecycle.Event.ON_CREATE)
        postDispatchRunnable(Lifecycle.Event.ON_START)
        super.onCreate()
        runBlocking {
            onCreatedBySuspend()
        }
    }

    override fun onTerminate() {
        postDispatchRunnable(Lifecycle.Event.ON_STOP)
        postDispatchRunnable(Lifecycle.Event.ON_DESTROY)
        super.onTerminate()
    }

    private fun postDispatchRunnable(event: Lifecycle.Event) {
        mLastDispatchRunnable?.run()
        mLastDispatchRunnable = DispatchRunnable(mRegistry, event)
        mHandle.postAtFrontOfQueue(mLastDispatchRunnable!!)
    }

    private class DispatchRunnable(private val mRegistry: LifecycleRegistry, private val mEvent: Lifecycle.Event) : Runnable {
        private var mWasExecuted: Boolean = false
        override fun run() {
            if (mWasExecuted) {
                return
            }
            mRegistry.handleLifecycleEvent(mEvent)
            mWasExecuted = true
        }
    }

    fun getLifecycleCoroutineScope(): LifecycleCoroutineScope {
        return lifecycleScope
    }


}