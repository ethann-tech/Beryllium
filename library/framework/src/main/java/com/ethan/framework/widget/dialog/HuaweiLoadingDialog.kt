package com.ethan.framework.widget.dialog

import android.content.Context
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.ethan.framework.R
import com.ethan.framework.databinding.DialogHuaweiLoadingViewBinding

class HuaweiLoadingDialog(context: Context) : AppCompatDialog(context, R.style.loading_dialog) {
    private lateinit var mBinding: DialogHuaweiLoadingViewBinding

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        mBinding = DialogHuaweiLoadingViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    fun setLoadingMessage(message: String) {
        if (message.isEmpty()) {
            mBinding.message.visibility = View.GONE
        } else {
            mBinding.message.text = message
            mBinding.message.visibility = View.VISIBLE
        }
    }

}