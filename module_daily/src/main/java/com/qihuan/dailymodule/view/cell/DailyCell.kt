package com.qihuan.dailymodule.view.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.model.bean.StoryBean
import me.drakeet.multitype.ItemViewBinder

/**
 * DailyCell
 * @author qi
 * @date 2018/7/11
 */
class DailyCell : ItemViewBinder<StoryBean, DailyCell.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_daily, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: StoryBean) {
        item.run {
            val url = if (images.isNotEmpty()) images[0] else ""
            holder.ivNews?.load(url, 4f)
            holder.tvNews?.text = title
            holder.tvLike?.text = storyExtraBean.popularity.toString()
            holder.tvComment?.text = storyExtraBean.comments.toString()
            holder.itemView.setOnClickListener {
                ARouter.getInstance()
                        .build(Routes.DAILY_DET_ACTIVITY)
                        .withInt("id", id)
                        .navigation()
            }
        }

    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val tvNews: TextView? = itemView.findViewById(R.id.tv_news)
        val tvLike: TextView? = itemView.findViewById(R.id.tv_like)
        val tvComment: TextView? = itemView.findViewById(R.id.tv_comment)
        val ivNews: ImageView? = itemView.findViewById(R.id.iv_news)
    }
}