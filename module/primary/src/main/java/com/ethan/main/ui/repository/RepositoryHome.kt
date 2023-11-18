package com.ethan.main.ui.repository

import com.ethan.common.BeanBanner
import com.ethan.network.api.ApiInterface
import com.ethan.network.manager.NetWorkManager
import com.ethan.network.repository.RepositoryBase
import javax.inject.Inject

open class RepositoryHome : RepositoryBase() {

    suspend fun requestHomeBanner(): MutableList<BeanBanner>? {
        return requestResponse { NetWorkManager.instance.provideService(ApiInterface::class.java).getHomeBanner() }
    }


}