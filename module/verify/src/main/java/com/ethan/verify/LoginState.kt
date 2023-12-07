package com.ethan.verify

class LoginState(var loginState: Int = NOT_LOGIN) {
    companion object {
        /**
         * 未登录
         */
        const val NOT_LOGIN = 0

        /**
         * 登录有效
         */
        const val LOGIN_VALID = 1

        /**
         * 登录中
         */
        const val LOGIN_ING = 2

        /**
         * 登录成功
         */
        const val LOGIN_SUCCESS = 3

        /**
         * 登录失败
         */
        const val LOGIN_FAIL = 4
    }
}