package com.qihuan.dailymodule.view.cell

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.model.bean.TopStoryBean
import me.drakeet.multitype.ItemViewBinder

/**
 * BannerCell
 * @author qi
 * @date 2018/7/11
 */
class BannerCell : ItemViewBinder<Array<TopStoryBean>, BannerCell.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.layout_banner, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemList: Array<TopStoryBean>) {
        holder.bannerLayout.setData(R.layout.item_daily_banner, itemList.asList(), null)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Array<TopStoryBean>, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, item, payloads)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), BGABanner.Adapter<View, TopStoryBean>, BGABanner.Delegate<View, TopStoryBean> {

        val bannerLayout: BGABanner = itemView as BGABanner

        init {
            bannerLayout.setDelegate(this)
            bannerLayout.setAdapter(this)
        }

        override fun fillBannerItem(banner: BGABanner?, itemView: View?, model: TopStoryBean?, position: Int) {
            val ivBanner: ImageView? = itemView?.findViewById(R.id.iv_banner)
            val tvBanner: TextView? = itemView?.findViewById(R.id.tv_banner)
            model?.run {
                ivBanner?.load(image)
                tvBanner?.text = title
            }
        }

        override fun onBannerItemClick(banner: BGABanner?, itemView: View?, model: TopStoryBean?, position: Int) {
            model?.run {
                ARouter.getInstance()
                        .build(Routes.DAILY_DET_ACTIVITY)
                        .withInt("id", id)
                        .navigation()
            }
        }

    }
}