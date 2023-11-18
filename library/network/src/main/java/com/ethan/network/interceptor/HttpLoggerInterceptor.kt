package com.ethan.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

class HttpLoggerInterceptor :HttpLoggingInterceptor.Logger {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override fun log(message: String) {
        mLogger.info("LOG:HttpLoggerInterceptor:log message={}", message)
    }
}