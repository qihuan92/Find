package com.qihuan.moviemodule.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qihuan.commonmodule.utils.loadImage
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectsBean

/**
 * MovieCardAdapter
 * @author qi
 * @date 2018/6/5
 */
class UsBoxMovieRankingAdapter : BaseQuickAdapter<SubjectsBean, BaseViewHolder>(R.layout.item_movie_ranking, null) {

    override fun convert(helper: BaseViewHolder, item: SubjectsBean?) {
        item?.apply {
            helper.loadImage(R.id.iv_movie, subject.images.medium, 4f)
                    .setText(R.id.tv_ranking, rank.toString())
                    .setText(R.id.tv_movie, subject.title)
                    .setText(R.id.tv_score, subject.rating.average.toString())
                    .setRating(R.id.rb_score, (subject.rating.average / 2f).toFloat())
        }
    }

}