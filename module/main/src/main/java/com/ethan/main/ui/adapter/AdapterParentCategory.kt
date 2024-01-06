package com.ethan.main.ui.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.DataBindingHolder
import com.ethan.common.R
import com.ethan.common.bean.BeanCategoryResponse
import com.ethan.main.databinding.ItemParentCategoryBinding
import io.github.uhsk.kit.android.dp2px

class AdapterParentCategory : BaseQuickAdapter<BeanCategoryResponse, DataBindingHolder<ItemParentCategoryBinding>>() {
    var mCurrentPosition = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: DataBindingHolder<ItemParentCategoryBinding>, position: Int, item: BeanCategoryResponse?) {
        val binding = holder.binding
        item?.let {
            binding.itemCategoryName.text = it.name
        }
        if (position == mCurrentPosition) {
            binding.itemIndicator.visibility = View.VISIBLE
            binding.itemCategoryName.background = getBgDrawable(R.color.white)
        } else {
            binding.itemIndicator.visibility = View.GONE
            when (position) {
                mCurrentPosition - 1 -> {
                    binding.itemCategoryName.background = getBgDrawable(R.color.color_f0f2f4, rightBottomRadius = 8.dp2px().toFloat())
                }

                mCurrentPosition + 1 -> {
                    binding.itemCategoryName.background = getBgDrawable(R.color.color_f0f2f4, rightTopRadius = 8.dp2px().toFloat())
                }

                else                 -> {
                    binding.itemCategoryName.background = getBgDrawable(R.color.color_f0f2f4)
                }
            }

        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): DataBindingHolder<ItemParentCategoryBinding> {
        return DataBindingHolder(ItemParentCategoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    /**
     * 设置背景
     * @param color 背景颜色
     * @param leftTopRadius top-left
     * @param rightTopRadius top-right
     * @param leftBottomRadius bottom-right
     * @param rightBottomRadius bottom-left
     */
    private fun getBgDrawable(@ColorRes color: Int, leftTopRadius: Float = 0.0f, rightTopRadius: Float = 0.0f, rightBottomRadius: Float = 0.0f, leftBottomRadius: Float = 0.0f): GradientDrawable {
        return GradientDrawable().apply {
            setColor(ResourcesCompat.getColor(context.resources, color, null))
            shape = GradientDrawable.RECTANGLE
            cornerRadii = floatArrayOf(leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius)
        }
    }

}