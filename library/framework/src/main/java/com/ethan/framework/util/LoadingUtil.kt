package com.ethan.framework.util

import android.app.Activity
import android.content.Context
import com.ethan.framework.widget.dialog.HuaweiLoadingDialog

class LoadingUtil(private val context: Context) {

    private val mLoadingView: HuaweiLoadingDialog by lazy { HuaweiLoadingDialog(context = context) }
    fun showLoading(text: String) {

        if (mLoadingView.isShowing) {
            mLoadingView.dismiss()
        }

        if (context is Activity && context.isFinishing) {
            return
        }

        mLoadingView.setLoadingMessage(message = text)
        mLoadingView.show()

    }


    fun hideLoading() {
        if (context is Activity && context.isFinishing) {
            return
        }
        mLoadingView.let {
            if (it.isShowing) it.dismiss()
        }
    }

}