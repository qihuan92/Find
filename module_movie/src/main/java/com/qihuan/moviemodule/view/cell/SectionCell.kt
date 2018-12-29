package com.qihuan.moviemodule.view.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.setVisible
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.vo.SectionVO
import me.drakeet.multitype.ItemViewBinder

/**
 * DailyCell
 * @author qi
 * @date 2018/7/11
 */
class SectionCell : ItemViewBinder<SectionVO, SectionCell.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_section_movie, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: SectionVO) {
        holder.tvTitle?.text = item.title
        holder.tvMore?.setVisible(item.isShowMore)
        holder.tvMore?.setOnClickListener {
            ARouter.getInstance()
                    .build(Routes.MOVIE_LIST_ACTIVITY)
                    .withInt(Routes.MOVIE_LIST_ACTIVITY_EXTRA_TYPE, item.type)
                    .withString(Routes.MOVIE_LIST_ACTIVITY_EXTRA_TITLE, item.title)
                    .navigation()
        }
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView? = itemView.findViewById(R.id.tv_section_title)
        val tvMore: TextView? = itemView.findViewById(R.id.tv_section_more)
    }
}