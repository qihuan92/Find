package com.qihuan.moviemodule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.lujun.androidtagview.TagContainerLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.AutoUpdatableAdapter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectBean
import kotlin.properties.Delegates


/**
 * MovieListAdapter
 * @author qi
 * @date 2018/10/18
 */
class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var itemList: List<SubjectBean> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        autoNotify(oldValue, newValue) { old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        item.run {
            // holder.tvRanking?.text = String.format("%02d", holder.adapterPosition + 1)
            holder.ivMovie?.load(images.small, 4f)
            holder.tvMovie?.text = title
            holder.tvYear?.text = original_title
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

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovie: ImageView? = itemView.findViewById(R.id.iv_item_movie)
        val tvMovie: TextView? = itemView.findViewById(R.id.tv_item_movie)
        val tvYear: TextView? = itemView.findViewById(R.id.tv_item_year)
        val tvOriginalTitle: TextView? = itemView.findViewById(R.id.tv_item_original_title)
        val rbScore: RatingBar? = itemView.findViewById(R.id.rb_item_score)
        val tvScore: TextView? = itemView.findViewById(R.id.tv_item_score)
        val tagGenres: TagContainerLayout? = itemView.findViewById(R.id.tag_item_genres)
    }
}