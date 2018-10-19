package com.qihuan.moviemodule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectBean
import org.jetbrains.annotations.NotNull


/**
 * MovieCardAdapter
 * @author qi
 * @date 2018/10/18
 */
class MovieCardAdapter : RecyclerView.Adapter<MovieCardAdapter.ViewHolder>() {

    var itemList: List<SubjectBean> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.ivCardMovie?.load(item.images.medium)
        holder.tvCardMovie?.text = item.title
        holder.rbScore?.rating = (item.rating.average / 2f).toFloat()
        holder.tvScore?.text = item.rating.average.toString()

        holder.itemView.setOnClickListener {
            ARouter.getInstance()
                    .build(Routes.MOVIE_DET_ACTIVITY)
                    .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, item.id)
                    .navigation()
        }
    }

    fun setMovieList(@NotNull itemList: List<SubjectBean>) {
        this.itemList = itemList
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCardMovie: ImageView? = itemView.findViewById(R.id.iv_card_movie)
        val tvCardMovie: TextView? = itemView.findViewById(R.id.tv_card_movie)
        val rbScore: RatingBar? = itemView.findViewById(R.id.rb_card_score)
        val tvScore: TextView? = itemView.findViewById(R.id.tv_card_score)
    }
}