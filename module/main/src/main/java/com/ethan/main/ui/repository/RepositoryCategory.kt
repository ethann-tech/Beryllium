package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanCategoryResponse
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

class RepositoryCategory : RepositoryBase() {

    suspend fun requestCategoryList(): ResponseBase<List<BeanCategoryResponse>> {
        return requestResponse2 { api.requestCategoryList() }
    }

}