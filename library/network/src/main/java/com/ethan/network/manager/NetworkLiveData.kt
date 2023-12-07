package com.ethan.network.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.ethan.framework.SingletonHolder
import kotlinx.coroutines.internal.synchronized

/**
 * https://blog.csdn.net/gdutxiaoxu/article/details/86660760
 */

class NetworkLiveData private constructor(context: Context) : LiveData<NetworkInfo?>() {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private var mNetworkReceiver: BroadcastReceiver
    private var mIntentFilter: IntentFilter
    private var mContext: Context

    init {
        mContext = context.applicationContext
        mNetworkReceiver = NetworkReceiver()
        mIntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }

   companion object :SingletonHolder<NetworkLiveData,Context>(::NetworkLiveData)

    override fun onActive() {
        super.onActive()
        mContext.registerReceiver(mNetworkReceiver, mIntentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        mContext.unregisterReceiver(mNetworkReceiver)
    }

    inner class NetworkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val manager: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo
            mLogger.info("LOG:NetworkReceiver:onReceive hasCode={}", NetworkLiveData.newInstance(mContext).hashCode())
            NetworkLiveData.newInstance(mContext).postValue(networkInfo)
            mLogger.info("LOG:NetworkReceiver:onReceive networkInfo={}", networkInfo)
        }
    }

}
