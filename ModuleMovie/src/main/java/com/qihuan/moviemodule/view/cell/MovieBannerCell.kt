package com.qihuan.moviemodule.view.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.view.adapter.MovieCardAdapter
import me.drakeet.multitype.ItemViewBinder
import org.jetbrains.annotations.NotNull

/**
 * DailyCell
 * @author qi
 * @date 2018/7/11
 */
class MovieBannerCell : ItemViewBinder<Array<SubjectBean>, MovieBannerCell.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.layout_list, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Array<SubjectBean>) {
        holder.setMovieList(item.toList())
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val rvList: RecyclerView? = itemView.findViewById(R.id.rv_list)
        val adapter = MovieCardAdapter()

        init {
            rvList?.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = this@ViewHolder.adapter
            }
        }

        fun setMovieList(@NotNull itemList: List<SubjectBean>) {
            adapter.setMovieList(itemList)
        }
    }
}