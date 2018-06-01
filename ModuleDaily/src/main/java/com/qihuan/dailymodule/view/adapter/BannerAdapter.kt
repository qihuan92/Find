package com.qihuan.dailymodule.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.qihuan.commonmodule.utils.load
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.model.bean.TopStoryBean

/**
 * BannerAdapter
 * @author qi
 * @date 2018/6/1
 */
class BannerAdapter : BGABanner.Adapter<View, TopStoryBean> {

    override fun fillBannerItem(banner: BGABanner?, itemView: View?, model: TopStoryBean?, position: Int) {
        val ivBanner = itemView?.findViewById<ImageView>(R.id.iv_banner)
        val tvBanner = itemView?.findViewById<TextView>(R.id.tv_banner)
        model?.run {
            ivBanner?.load(image)
            tvBanner?.text = title
        }
    }
}