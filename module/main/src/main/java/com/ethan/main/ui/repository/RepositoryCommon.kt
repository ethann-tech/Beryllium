package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanResponseArticle
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

open class RepositoryCommon:RepositoryBase() {
    suspend fun requestArticleListByCid(page: Int, pageSize: Int, cid: Int): ResponseBase<BeanResponseArticle> {
        return requestResponse2 { api.getArticleListByCid(page = page, cid = cid, pageSize = pageSize) }
    }


}