package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanBanner
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.network.api.ApiInterface
import com.ethan.network.manager.NetWorkManager
import com.ethan.network.repository.RepositoryBase
import com.ethan.network.response.ResponseBase

open class RepositoryHome : RepositoryBase() {

    suspend fun requestHomeBanner(): MutableList<BeanBanner>? {
        return requestResponse { api.getHomeBanner() }
    }

    /**
     * 项目分类接口
     */
    suspend fun requestProjectCategoryList():ResponseBase<List<BeanProjectCategory>>{
        return requestResponse2 { api.requestProjectCategory() }
    }

}