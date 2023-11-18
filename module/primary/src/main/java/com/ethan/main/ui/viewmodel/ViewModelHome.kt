package com.ethan.main.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.BeanBanner
import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ViewModelHome : BaseViewModel() {
    private val _bannerListData = MutableLiveData<MutableList<BeanBanner>>()

    val mBannerListData: MutableLiveData<MutableList<BeanBanner>> = _bannerListData
    private val repositoryHome = RepositoryHome()

    fun requestHomeBanner() {
        viewModelScope.launch {
            _bannerListData.value = repositoryHome.requestHomeBanner()
        }
    }

}