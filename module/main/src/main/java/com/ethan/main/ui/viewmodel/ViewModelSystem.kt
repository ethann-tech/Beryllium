package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanArticle
import com.ethan.common.bean.BeanResponseArticle
import com.ethan.common.bean.BeanSystem
import com.ethan.main.ui.repository.RepositorySystem
import com.ethan.network.response.ResponseBase
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ViewModelSystem(app: Application) : BaseViewModel(app = app) {
    private val mRepositorySystem by inject<RepositorySystem>(RepositorySystem::class.java)

    private val _systemList: MutableLiveData<List<BeanSystem>> = MutableLiveData()
    val mSystemList = _systemList

    private val _errorSystem: MutableLiveData<String> = MutableLiveData()
    private val mErrorSystem = _errorSystem

    private val _articleList: MutableLiveData<List<BeanArticle>> = MutableLiveData()
    val mArticleList: MutableLiveData<List<BeanArticle>> = _articleList

    private val pageSize = 10
    var page = 0

    fun requestSystemList() {
        viewModelScope.launch {
            val mResponse: ResponseBase<List<BeanSystem>> = mRepositorySystem.requestSystemList()
            if (mResponse.isSuccess()) {
                _systemList.value = (mResponse.data)
            } else {
                _errorSystem.postValue(mResponse.message)
            }
        }
    }

    fun requestArticleListByCid(cid: Int) {
        viewModelScope.launch {
            val mResponse: ResponseBase<BeanResponseArticle> = mRepositorySystem.requestArticleListByCid(page = page, pageSize = pageSize, cid = cid)
            if (mResponse.isSuccess()) {
                _articleList.value = mResponse.data?.datas
            } else {
                _errorSystem.value = mResponse.message
            }
        }

    }
}