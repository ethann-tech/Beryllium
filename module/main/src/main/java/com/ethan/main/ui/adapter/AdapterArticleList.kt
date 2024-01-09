package com.ethan.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseDifferAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.bean.BeanArticle
import com.ethan.main.R
import com.ethan.main.databinding.ItemArticleListViewBinding
import io.github.uhsk.kit.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN
import io.github.uhsk.kit.format
import io.github.uhsk.kit.toDate

class AdapterArticleList : BaseDifferAdapter<BeanArticle, DataBindingHolder<ItemArticleListViewBinding>>(DiffCallBack()) {

    override fun onBindViewHolder(holder: DataBindingHolder<ItemArticleListViewBinding>, position: Int, item: BeanArticle?) {
        val mBinding = holder.binding
        item?.let {
            mBinding.tvTitle.text = it.title
            mBinding.tvDate.text = it.publishTime.toDate().format(pattern = DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MIN)
            mBinding.tvAuthor.text = context.resources.getString(R.string.mine_author).plus(it.author.ifEmpty { it.shareUser })
            mBinding.tvChapter.text =it.superChapterName.plus(other = "/").plus(it.chapterName)
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemArticleListViewBinding> {
        return DataBindingHolder<ItemArticleListViewBinding>(R.layout.item_article_list_view, parent)
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