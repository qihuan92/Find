package com.qihuan.moviemodule.view.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.lujun.androidtagview.TagContainerLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectBean
import me.drakeet.multitype.ItemViewBinder

/**
 * DailyCell
 * @author qi
 * @date 2018/7/11
 */
class MovieCell : ItemViewBinder<SubjectBean, MovieCell.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_movie_ranking, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: SubjectBean) {
        item.run {
            // holder.tvRanking?.text = String.format("%02d", holder.adapterPosition + 1)
            holder.ivMovie?.load(images.small, 4f)
            holder.tvMovie?.text = title
            holder.tvYear?.text = "[$year]"
            holder.tvScore?.text = rating.average.toString()
            holder.rbScore?.rating = (rating.average / 2f).toFloat()
            holder.tagGenres?.tags = genres
            holder.itemView.setOnClickListener {
                ARouter.getInstance()
                        .build(Routes.MOVIE_DET_ACTIVITY)
                        .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                        .navigation()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovie: ImageView? = itemView.findViewById(R.id.iv_ranking_movie)
        val tvMovie: TextView? = itemView.findViewById(R.id.tv_ranking_movie)
        val tvYear: TextView? = itemView.findViewById(R.id.tv_ranking_year)
        val rbScore: RatingBar? = itemView.findViewById(R.id.rb_ranking_score)
        val tvScore: TextView? = itemView.findViewById(R.id.tv_ranking_score)
        val tagGenres: TagContainerLayout? = itemView.findViewById(R.id.tag_ranking_genres)
        val tvRanking: TextView? = itemView.findViewById(R.id.tv_ranking)
    }
}