package com.ethan.verify.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ethan.common.constant.AppConstant
import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.FragmentBusinessBase
import com.ethan.framework.util.ToastUtil
import com.ethan.network.manager.NetworkLiveData
import com.ethan.verify.LoginState
import com.ethan.verify.R
import com.ethan.verify.databinding.FragmentLoginBinding
import com.ethan.verify.viewmodel.ViewModelVerify
import com.google.gson.Gson
import com.jakewharton.rxbinding4.view.clicks
import io.github.uhsk.kit.android.dataStore
import io.github.uhsk.kit.androidx.datastore.putString
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import zlc.season.butterfly.Butterfly
import zlc.season.butterfly.annotation.Agile
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
@Agile(scheme = RouterPath.FRAGMENT_VERIFY_LOGIN)
class FragmentLogin : FragmentBusinessBase<FragmentLoginBinding>() {

    private val mViewMode by inject<ViewModelVerify>()
    private val mGson: Gson by inject<Gson>()
    override fun getLayoutResId(): Int = R.layout.fragment_login

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        mBinding.lifecycleOwner = this
        mBinding.vm =mViewMode
        mLogger.info("LOG:FragmentLogin:initView dataStore.hashCode={}", mContext.dataStore.hashCode())
        mBinding.btnLogin.clicks().throttleLast(1, TimeUnit.SECONDS).subscribe {
            val username = mBinding.editUserName.text.toString().trim()
            val password = mBinding.editPassword.text.toString().trim()
            mViewMode.requestLogin(username = username, password = password)
        }

        mViewMode.loginStateLiveData.observe(this) {
            when (it.loginState) {
                LoginState.NOT_LOGIN     -> {
                    mLogger.info("LOG:FragmentLogin:initView 未登录")
                }

                LoginState.LOGIN_ING     -> {
                    showLoading("登录中")
                }

                LoginState.LOGIN_SUCCESS -> {
                    ToastUtil.showToast("登录成功")
                    hideLoading()
                }

                LoginState.LOGIN_VALID   -> {
                    ToastUtil.showToast("登录失败")
                    hideLoading()
                }

                LoginState.LOGIN_FAIL    -> {
                    hideLoading()
                }
            }
        }
        mViewMode.mLoginResultLiveData.observe(this) {
            mLogger.info("LOG:FragmentLogin:initView it={}", mGson.toJson(it))
            if (it != null) {
                ToastUtil.showToast(mGson.toJson(it))
                lifecycleScope.launch {
                    mContext.dataStore.putString(AppConstant.USER_INFO, value = mGson.toJson(it))
//                    Butterfly.agile(scheme = RouterPath.ACTIVITY_MAIN_HOME).carry(context = mContext)
//                    requireActivity().finish()
                }
            }
        }
        mLogger.info("LOG:FragmentLogin:initView hasCode={}", NetworkLiveData.newInstance(mContext).hashCode())
        NetworkLiveData.newInstance(mContext).observe(this){
            if (it==null){
                mLogger.info("LOG:FragmentLogin:initView it->network is null")
            }else{
                mLogger.info("LOG:FragmentLogin:initView networkinfo={}",it.typeName)
            }
        }
    }

    override fun initData() {
        super.initData()
    }

}