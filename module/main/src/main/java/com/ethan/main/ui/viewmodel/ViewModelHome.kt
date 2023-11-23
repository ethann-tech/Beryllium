package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanBanner
import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


class ViewModelHome (application: Application,private val mRepositoryHome:RepositoryHome): BaseViewModel(app = application) {
    private val _bannerListData = MutableLiveData<MutableList<BeanBanner>>()

    val mBannerListData: MutableLiveData<MutableList<BeanBanner>> = _bannerListData


    fun requestHomeBanner() {
        viewModelScope.launch {
            _bannerListData.value = mRepositoryHome.requestHomeBanner()
        }
    }

}