package com.ethan.verify.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanLogin
import com.ethan.network.viewmodel.BaseViewModel
import com.ethan.verify.LoginState
import com.ethan.verify.repository.RepositoryVerify
import kotlinx.coroutines.launch

class ViewModelVerify(application: Application, val repositoryVerify: RepositoryVerify) : BaseViewModel(app = application) {

    private val _loginResultLiveData = MutableLiveData<BeanLogin?>()
    val mLoginResultLiveData: LiveData<BeanLogin?> = _loginResultLiveData

    private val _loginStateLiveData = MutableLiveData<LoginState>()
    val loginStateLiveData: LiveData<LoginState> = _loginStateLiveData

    fun requestLogin(username: String, password: String) {
        viewModelScope.launch {
            val mResponse = repositoryVerify.requestLogin(username = username, password = password)
            mLogger.info("LOG:ViewModelVerify:requestLogin mResponse={}", mGson.toJson(mResponse))
            if (mResponse.isFailed()) {
                _loginStateLiveData.value = LoginState(loginState = LoginState.LOGIN_FAIL)
                _loginResultLiveData.value = null
            } else {
                _loginStateLiveData.value = LoginState(loginState = LoginState.LOGIN_SUCCESS)
                _loginResultLiveData.value = mResponse.data

            }

        }
    }






}