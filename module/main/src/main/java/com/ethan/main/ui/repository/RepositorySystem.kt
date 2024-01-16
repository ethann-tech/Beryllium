package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanSystem
import com.ethan.network.response.ResponseBase

class RepositorySystem : RepositoryCommon() {
    suspend fun requestSystemList(): ResponseBase<List<BeanSystem>> {
        return requestResponse2 { api.requestSystemList() }
    }
}