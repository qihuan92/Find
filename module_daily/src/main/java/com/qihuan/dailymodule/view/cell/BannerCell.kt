package com.qihuan.dailymodule.view.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.banner.BannerLayout
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.dp2px
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
        val root = BannerLayout<TopStoryBean>(parent.context)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.context.dp2px(175f))
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, itemList: Array<TopStoryBean>) {
        holder.bannerLayout.setData(itemList.asList())
    }

    class ViewHolder(itemView: BannerLayout<TopStoryBean>) : RecyclerView.ViewHolder(itemView) {

        val bannerLayout: BannerLayout<TopStoryBean> = itemView

        init {
            bannerLayout.setItemView(R.layout.item_daily_banner)
            bannerLayout.setDot(R.drawable.selector_banner_point)
            bannerLayout.itemClick { view, data, position ->
                ARouter.getInstance()
                        .build(Routes.DAILY_DET_ACTIVITY)
                        .withInt("id", data.id)
                        .navigation()
            }
            bannerLayout.loadItem { view, data, position ->
                val ivBanner: ImageView? = view.findViewById(R.id.iv_banner)
                val tvBanner: TextView? = view.findViewById(R.id.tv_banner)
                ivBanner?.load(data.image)
                tvBanner?.text = data.title
            }
        }
    }
}