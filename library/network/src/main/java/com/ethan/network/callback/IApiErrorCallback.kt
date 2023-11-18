package com.ethan.network.callback

import com.ethan.framework.util.ToastUtil


interface IApiErrorCallback {
    /**
     * 错误回调处理
     */
    fun onError(code: Int?, error: String?) {
        ToastUtil.showToast(error?:"")
    }

    /**
     * 登录失效处理
     */
    fun onLoginFail(code: Int?, error: String?) {
        ToastUtil.showToast(error?:"")
    }
}