package com.ethan.network.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.get


open class BaseViewModel(app:Application) : AndroidViewModel(app) {
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    protected val mGson :Gson =get(clazz = Gson::class.java)

}