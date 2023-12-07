package com.ethan.framework.util

import android.app.Activity
import android.content.Context
import com.ethan.framework.widget.dialog.HuaweiLoadingDialog

class LoadingUtil(private val context: Context) {

    private lateinit var mLoadingView: HuaweiLoadingDialog
    fun showLoading(text: String) {
        if (this::mLoadingView.isInitialized) {
            mLoadingView = HuaweiLoadingDialog(context = context)
        }
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
        if (this::mLoadingView.isInitialized.not()){
            return
        }
        mLoadingView.let {
            if (it.isShowing) it.dismiss()
        }
    }

}