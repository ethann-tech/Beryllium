package com.ethan.framework.util

import android.view.Gravity
import com.ethan.framework.R
import com.hjq.toast.Toaster

class ToastUtil {
    companion object {
        fun showToast(text: String, gravity: Int = Gravity.TOP) {
            Toaster.setView(R.layout.toast_custom_view)
            Toaster.setGravity(gravity)
            Toaster.show(text)
        }


    }
}