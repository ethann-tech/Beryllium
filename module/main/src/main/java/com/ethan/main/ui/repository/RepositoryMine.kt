package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanBanner
import com.ethan.common.bean.BeanRecommendResponse
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

class RepositoryMine : RepositoryBase() {
    suspend fun requestRecommendList(page: Int, pageSize: Int): ResponseBase<BeanRecommendResponse> {
        return requestResponse2 { api.getHomeArticleList(page = page, pageSize = pageSize) }
    }
}