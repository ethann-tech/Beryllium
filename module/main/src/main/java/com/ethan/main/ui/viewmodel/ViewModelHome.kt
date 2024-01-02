package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanBanner
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


class ViewModelHome (application: Application,private val mRepositoryHome:RepositoryHome): BaseViewModel(app = application) {
    private val _bannerListData = MutableLiveData<MutableList<BeanBanner>>()

    val mBannerListData: MutableLiveData<MutableList<BeanBanner>> = _bannerListData

    private val _errorMsg:MutableLiveData<String>  = MutableLiveData()
    val mErrorMsgLiveData =_errorMsg

    private val _projectCategoryListResult :MutableLiveData<List<BeanProjectCategory>> =MutableLiveData()
    val mProjectCategoryList =_projectCategoryListResult
    fun requestHomeBanner() {
        viewModelScope.launch {
            _bannerListData.value = mRepositoryHome.requestHomeBanner()
        }
    }
    fun requestProjectCategoryList() {
        viewModelScope.launch {
            val response = mRepositoryHome.requestProjectCategoryList()
            if (response.isFailed()) {
                _errorMsg.value = response.message
            } else {
                _projectCategoryListResult.value = response.data!!
            }
            mLogger.info("LOG:ViewModelHome:requestProjectCategoryList response={}", mGson.toJson(response))
        }
    }

}