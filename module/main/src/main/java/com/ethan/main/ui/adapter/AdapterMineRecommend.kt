package com.ethan.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanArticle
import com.ethan.main.R
import com.ethan.main.databinding.ItemMineRecommendBinding
import io.github.uhsk.kit.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN
import io.github.uhsk.kit.format
import io.github.uhsk.kit.toDate

class AdapterMineRecommend : BaseDifferAdapter<BeanArticle, DataBindingHolder<ItemMineRecommendBinding>>(DiffCallBack()) {

    override fun onBindViewHolder(holder: DataBindingHolder<ItemMineRecommendBinding>, position: Int, item: BeanArticle?) {
        val mBinding = holder.binding
        item?.let {
            mBinding.tvTitle.text = it.title
            mBinding.tvDate.text = it.publishTime.toDate().format(pattern = DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN)
            mBinding.tvAuthor.text = context.resources.getString(R.string.mine_author).plus(it.author.ifEmpty { it.shareUser })
            mBinding.tvChapter.text =it.superChapterName.plus(other = "/").plus(it.chapterName)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemMineRecommendBinding> {
        return DataBindingHolder<ItemMineRecommendBinding>(R.layout.item_mine_recommend, parent)
    }
    class DiffCallBack : DiffUtil.ItemCallback<BeanArticle>() {
        override fun areItemsTheSame(oldItem: BeanArticle, newItem: BeanArticle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BeanArticle, newItem: BeanArticle): Boolean {
            return oldItem.title == newItem.title && oldItem.author == newItem.author && oldItem.link == newItem.link
        }
    }
}