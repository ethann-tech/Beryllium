package com.ethan.network.exception

import com.ethan.network.exception.ERROR
import java.io.IOException

/**
 * 结果异常类
 * 服务器非200状态，对应的异常
 */
open class ApiException : Exception {
     var errCode: Int
     var errMsg: String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errMsg
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.errCode = code
        this.errMsg = msg
    }
}

/**
 * 无网络连接异常
 */
class NoNetWorkException : IOException {
    private var errCode: Int
    private var errMsg: String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errMsg
    }
}
