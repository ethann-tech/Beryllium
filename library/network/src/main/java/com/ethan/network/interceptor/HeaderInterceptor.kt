package com.ethan.network.interceptor

import com.ethan.network.HttpConstants
import com.ethan.network.manager.CookiesManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author mingyan.su
 * @date   2023/3/27 07:25
 * @desc   头信息拦截器
 * 添加头信息
 */
class HeaderInterceptor : Interceptor {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")

        val host = request.url.host
        val url = request.url.toString()

        //给有需要的接口添加Cookies
        if (host.isNotEmpty() && (url.contains(HttpConstants.COLLECTION_WEBSITE) || url.contains(HttpConstants.NOT_COLLECTION_WEBSITE) || url.contains(HttpConstants.ARTICLE_WEBSITE) || url.contains(HttpConstants.COIN_WEBSITE))) {
            val cookies = CookiesManager.getCookies()

            mLogger.error("HeaderInterceptor:intercept:cookies={}",cookies)
            if (!cookies.isNullOrEmpty()) {
                newBuilder.addHeader(HttpConstants.KEY_COOKIE, cookies)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}