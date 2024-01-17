package com.ethan.common.config

import android.content.Context
import com.ethan.common.bean.BeanUserInfo
import com.ethan.common.constant.AppConstant
import com.google.gson.Gson
import io.github.uhsk.kit.android.dataStore
import io.github.uhsk.kit.jetpack.datastore.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class UserManager {
    private val mGson = get<Gson>(Gson::class.java)
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(UserManager::class.java)
    private object SingleHolder {
        val INSTANCE = UserManager()
    }
    companion object {
        val INSTANCE = SingleHolder.INSTANCE
    }

    fun isLogin(context: Context): Boolean = runBlocking {
        return@runBlocking withContext(context = Dispatchers.IO) {
            val userInfo = context.dataStore.getString(AppConstant.USER_INFO)
            return@withContext userInfo.isNotEmpty()
        }
    }


    fun getUserInfo(context: Context): BeanUserInfo? = runBlocking {
        val userInfoStr = context.dataStore.getString(AppConstant.USER_INFO)
        if (userInfoStr.isNotEmpty()) {
            mGson.fromJson(userInfoStr, BeanUserInfo::class.java)
        } else {
            null
        }
    }

}