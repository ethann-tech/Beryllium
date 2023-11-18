package com.ethan.network.interceptor

import com.ethan.network.HttpConstants
import com.ethan.network.manager.CookiesManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author mingyan.su
 * @date   2023/3/27 07:26
 * @desc   Cookies拦截器
 */
class CookiesInterceptor : Interceptor {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val response = chain.proceed(newBuilder.build())
        val url = request.url.toString()
        val host = request.url.host

        // set-cookie maybe has multi, login to save cookie
        if ((url.contains(HttpConstants.KEY_SAVE_USER_LOGIN) || url.contains(HttpConstants.KEY_SAVE_USER_REGISTER))
                && response.headers(HttpConstants.KEY_SET_COOKIE).isNotEmpty()
        ) {
            val cookies = response.headers(HttpConstants.KEY_SET_COOKIE)
            val cookiesStr = CookiesManager.encodeCookie(cookies)
            CookiesManager.saveCookies(cookiesStr)
            mLogger.info("LOG:CookiesInterceptor:intercept cookies={} ", cookies)
        }
        return response
    }
}