package com.qihuan.dailymodule.view.cell

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qihuan.dailymodule.R
import me.drakeet.multitype.ItemViewBinder

/**
 * DailyCell
 * @author qi
 * @date 2018/7/11
 */
class DailySectionCell : ItemViewBinder<String, DailySectionCell.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_date, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        holder.tvNewsDate?.text = item
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvNewsDate: TextView? = itemView?.findViewById(R.id.tv_news_date)
    }
}