package com.ethan.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseSingleItemAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.config.UserManager
import com.ethan.main.R
import com.ethan.main.databinding.FragmentMineHeaderBinding
import com.google.gson.Gson
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import io.github.uhsk.kit.android.dp2px
import org.koin.java.KoinJavaComponent.inject

class AdapterMineHeader : BaseSingleItemAdapter<Any, DataBindingHolder<FragmentMineHeaderBinding>>() {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    private val mGson by inject<Gson>(Gson::class.java)
    override fun onBindViewHolder(holder: DataBindingHolder<FragmentMineHeaderBinding>, item: Any?) {
        val statusBarHeight = UltimateBarX.getStatusBarHeight()
        holder.binding.layoutUserInfo.setPadding(16.dp2px(), statusBarHeight + 16.dp2px(), 16.dp2px(), 16.dp2px())
        val mContext = holder.binding.root.context
        if (UserManager.INSTANCE.isLogin(mContext)) {
            val userInfo = UserManager.INSTANCE.getUserInfo(mContext)
            userInfo?.let {
                mLogger.info("LOG:AdapterMineHeader:onBindViewHolder userInfo={}", mGson.toJson(userInfo))
                holder.binding.tvUsername.text = it.username
                holder.binding.tvDesc.text = it.email
            }
        }

    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<FragmentMineHeaderBinding> {
        return DataBindingHolder<FragmentMineHeaderBinding>(R.layout.fragment_mine_header, parent)
    }

}