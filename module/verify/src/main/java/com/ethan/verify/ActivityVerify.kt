package com.ethan.verify

import com.ethan.common.constant.RouterPath
import com.ethan.framework.base.ActivityBusinessBase
import com.ethan.verify.databinding.ActivityVerifyBinding
import zlc.season.butterfly.Butterfly
import zlc.season.butterfly.annotation.Agile

@Agile(scheme = RouterPath.ACTIVITY_VERIFY_LOGIN)
class ActivityVerify : ActivityBusinessBase<ActivityVerifyBinding>() {

    override fun layoutResId(): Int = R.layout.activity_verify
    override fun initView() {
        super.initView()
        Butterfly.agile(RouterPath.FRAGMENT_VERIFY_LOGIN).carry(context = mContext) //        replaceFragment(mBinding.fragmentContainerView.id, FragmentLogin())
    }


    override fun initListener() {

    }

    override fun initWindowAttributes() {
    }
}