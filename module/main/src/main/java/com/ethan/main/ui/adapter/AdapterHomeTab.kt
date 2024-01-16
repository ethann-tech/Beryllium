package com.ethan.main.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanArticle
import com.ethan.common.constant.RouterPath
import com.ethan.common.util.clipViewCornerRadius
import com.ethan.glide.setUrl
import com.ethan.main.R
import com.ethan.main.databinding.ItemHomeTabBinding
import com.google.gson.Gson
import io.github.uhsk.kit.android.dp2px
import io.github.uhsk.kit.android.view.setOnNoDoubleClickListener
import org.koin.java.KoinJavaComponent.inject
import zlc.season.butterfly.Butterfly

class AdapterHomeTab : BaseQuickAdapter<BeanArticle, DataBindingHolder<ItemHomeTabBinding>>() {
    private val mGson by inject<Gson>(Gson::class.java)
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun onBindViewHolder(holder: DataBindingHolder<ItemHomeTabBinding>, position: Int, item: BeanArticle?) {
        val mBinding = holder.binding
        mLogger.info("LOG:AdapterHomeTab:onBindViewHolder item={}", mGson.toJson(item))
        item?.let {
            mBinding.apply {
                tvTitle.text =it.title
                tvSubTitle.text =it.desc
                tvAuthorName.text =it.author
                tvTime.text =item.niceDate
                ivMainIcon.setUrl(it.envelopePic)
                ivMainIcon.setOnNoDoubleClickListener {
                    Butterfly.agile(scheme = RouterPath.ACTIVITY_ARTICLE_DETAIL).params("title" to item.title, "url" to item.link).carry(context = it.context)
                }
                root.clipViewCornerRadius(8.dp2px())
            }
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemHomeTabBinding> {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_home_tab, parent, false)
        return DataBindingHolder<ItemHomeTabBinding>(rootView)
    }

}