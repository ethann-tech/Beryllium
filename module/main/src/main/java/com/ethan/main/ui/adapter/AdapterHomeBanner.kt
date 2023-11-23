package com.ethan.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethan.common.bean.BeanBanner
import com.ethan.main.R
import com.ethan.main.databinding.ItemHomeBannerBinding
import com.youth.banner.adapter.BannerAdapter

class AdapterHomeBanner constructor(list: List<BeanBanner>) : BannerAdapter<BeanBanner, AdapterHomeBanner.MyBannerHolder>(list) {


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): MyBannerHolder {
        val rootView: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_home_banner, parent, false)
        rootView.layoutParams.apply {
            height = ViewGroup.LayoutParams.MATCH_PARENT
            width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        return MyBannerHolder(rootView)
    }

    override fun onBindView(holder: MyBannerHolder, data: BeanBanner, position: Int, size: Int) {

        Glide.with(holder.getBinding().root.context).load(data.imagePath).into(holder.getBinding().image)
    }


    class MyBannerHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mBinding: ItemHomeBannerBinding

        init {
            mBinding = ItemHomeBannerBinding.bind(view)

        }

        fun getBinding(): ItemHomeBannerBinding = mBinding


    }

}