package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanResponseArticle
import com.ethan.common.bean.BeanSystem
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

class RepositorySystem : RepositoryBase() {
    suspend fun requestSystemList(): ResponseBase<List<BeanSystem>> {
        return requestResponse2 { api.requestSystemList() }
    }
    suspend fun requestArticleListByCid(page: Int, pageSize: Int, cid: Int): ResponseBase<BeanResponseArticle> {
        return requestResponse2 { api.getArticleListByCid(page = page, cid = cid, pageSize = pageSize) }
    }
}