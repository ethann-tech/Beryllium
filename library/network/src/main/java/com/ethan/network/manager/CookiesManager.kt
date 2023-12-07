package com.ethan.network.manager

import com.ethan.common.constant.AppConstant
import com.ethan.common.util.LogUtil
import com.tencent.mmkv.MMKV


object CookiesManager {


    /**
     * 保存Cookies
     * @param cookies
     */
    fun saveCookies(cookies: String) {
        val mmkv = MMKV.defaultMMKV()
        mmkv.encode(AppConstant.HTTP_COOKIES_INFO, cookies)
    }

    /**
     * 获取Cookies
     * @return cookies
     */
    fun getCookies(): String {
        val mmkv = MMKV.defaultMMKV()
        return mmkv.decodeString(AppConstant.HTTP_COOKIES_INFO, "") ?: ""
    }

    /**
     * 清除Cookies
     * @param cookies
     */
    fun clearCookies() {
        saveCookies("")
    }

    /**
     * 解析Cookies
     * @param cookies
     */
    fun encodeCookie(cookies: List<String>?): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies?.map { cookie ->
            cookie.split(";".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        }
            ?.forEach {
                it.filterNot(set::contains)
                    .forEach { set.run { add(it) } }
            }
        LogUtil.info("LOG:CookiesManager:encodeCookie javaClass={}", cookies)

        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie)
                .append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }
}