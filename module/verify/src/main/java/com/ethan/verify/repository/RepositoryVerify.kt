package com.ethan.verify.repository

import com.ethan.common.bean.BeanUserInfo
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

open class RepositoryVerify : RepositoryBase() {

    suspend fun requestLogin(username: String, password: String): ResponseBase<BeanUserInfo> {
        return requestResponse2 {
            api.requestLogin(username = username, password = password)
        }
    }
}