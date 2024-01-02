package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanArticle
import com.ethan.main.ui.repository.RepositoryMine
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class ViewModelMine(application: Application, private val mRepositoryMine: RepositoryMine) : BaseViewModel(app = application) {

    private val _RecommendData = MutableLiveData<MutableList<BeanArticle>>()
    val mRecommendListData: MutableLiveData<MutableList<BeanArticle>> = _RecommendData

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val mErrorMsgLiveData = _errorMsg
     var page = 0
    private val pageSize =10
    fun requestRecommendList(page: Int) {
        viewModelScope.launch {
            val response = mRepositoryMine.requestRecommendList(page = page, pageSize = pageSize)
            mLogger.info("LOG:ViewModelMine:requestRecommendList response={}", mGson.toJson(response))
            if (response.isSuccess()) {
                _RecommendData.value = response.data?.datas?.toMutableList()
            } else {
                _errorMsg.value = response.message
            }
        }
    }
}