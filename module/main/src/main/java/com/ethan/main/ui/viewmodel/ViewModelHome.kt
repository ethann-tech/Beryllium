package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanArticle
import com.ethan.common.bean.BeanBanner
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.common.bean.BeanResponseArticle
import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.network.response.ResponseBase
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


class ViewModelHome (application: Application,private val mRepositoryHome:RepositoryHome): BaseViewModel(app = application) {
    private val _bannerListData = MutableLiveData<MutableList<BeanBanner>>()

    val mBannerListData: MutableLiveData<MutableList<BeanBanner>> = _bannerListData

    private val _errorMsg:MutableLiveData<String>  = MutableLiveData()
    val mErrorMsgLiveData =_errorMsg

    private val _projectCategoryListResult :MutableLiveData<List<BeanProjectCategory>> =MutableLiveData()
    val mProjectCategoryList =_projectCategoryListResult

    private val _articleList: MutableLiveData<List<BeanArticle>> = MutableLiveData()
    val mArticleList: MutableLiveData<List<BeanArticle>> = _articleList

    private val pageSize = 10
     var page = 0

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

    fun requestArticleListByCid(cid: Int) {
        viewModelScope.launch {
            val mResponse: ResponseBase<BeanResponseArticle> = mRepositoryHome.requestArticleListByCid(page = page, pageSize = pageSize, cid = cid)
            if (mResponse.isSuccess()) {
                _articleList.value = mResponse.data?.datas
            } else {
                _errorMsg.value = mResponse.message
            }
        }
    }

}