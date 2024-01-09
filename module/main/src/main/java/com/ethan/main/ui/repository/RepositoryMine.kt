package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanResponseArticle
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

class RepositoryMine : RepositoryBase() {
    suspend fun requestRecommendList(page: Int, pageSize: Int): ResponseBase<BeanResponseArticle> {
        return requestResponse2 { api.getHomeArticleList(page = page, pageSize = pageSize) }
    }
}