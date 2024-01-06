package com.ethan.main.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.common.bean.BeanCategoryResponse
import com.ethan.common.bean.BeanCategorySub
import com.ethan.main.ui.repository.RepositoryCategory
import com.ethan.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ViewModelCategory(application: Application) : BaseViewModel(app = application) {

    private val mRepositoryCategory: RepositoryCategory by inject(RepositoryCategory::class.java)

    private val _ListCategory = MutableLiveData<List<BeanCategoryResponse>>()
    val mListCategory =_ListCategory

    private val _errorMsg:MutableLiveData<String>  = MutableLiveData()
    val mErrorMsgLiveData =_errorMsg
    fun requestCategoryList(){

        viewModelScope.launch {
            val response = mRepositoryCategory.requestCategoryList()
            if (response.isSuccess()){
                _ListCategory.value =response.data!!
            }else{
                _errorMsg.value = response.message
            }
        }

    }


}