package com.ethan.verify.model

import android.text.TextUtils

class ModelLogin(var username: String = "", var password: String = "") {

    fun isValid(): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && password.length >= 6
    }
}