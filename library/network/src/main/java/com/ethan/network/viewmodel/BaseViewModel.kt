package com.ethan.network.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.network.exception.ApiException
import com.ethan.network.exception.ERROR
import com.ethan.network.exception.ExceptionHandler
import com.ethan.network.flow.requestFlow
import com.ethan.network.response.ResponseBase
import com.ethan.network.callback.IApiErrorCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout


open class BaseViewModel(app:Application) : AndroidViewModel(app) {
    protected val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

}