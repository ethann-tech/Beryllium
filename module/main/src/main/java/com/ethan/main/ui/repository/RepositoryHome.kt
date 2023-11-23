package com.ethan.main.ui.repository

import com.ethan.common.bean.BeanBanner
import com.ethan.network.api.ApiInterface
import com.ethan.network.manager.NetWorkManager
import com.ethan.network.repository.RepositoryBase

open class RepositoryHome : RepositoryBase() {

    suspend fun requestHomeBanner(): MutableList<BeanBanner>? {
        return requestResponse { NetWorkManager.instance.provideService(ApiInterface::class.java).getHomeBanner() }
    }


}