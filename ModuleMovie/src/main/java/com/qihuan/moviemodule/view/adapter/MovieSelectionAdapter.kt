package com.qihuan.moviemodule.view.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qihuan.commonmodule.utils.addTags
import com.qihuan.commonmodule.utils.loadImage
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.MovieSectionItemBean

/**
 * MovieSelectionAdapter
 * @author qi
 * @date 2018/6/6
 */
class MovieSelectionAdapter : BaseSectionQuickAdapter<MovieSectionItemBean, BaseViewHolder>(R.layout.item_movie_ranking, R.layout.item_section_movie, null) {


    override fun convertHead(helper: BaseViewHolder, item: MovieSectionItemBean?) {
        item?.apply {
            helper.setText(R.id.tv_title, header ?: "")
                    .addOnClickListener(R.id.tv_more)
        }
    }

    override fun convert(helper: BaseViewHolder, item: MovieSectionItemBean?) {
        item?.t?.apply {
            helper.loadImage(R.id.iv_movie, images.small, 4f)
                    .setText(R.id.tv_ranking, String.format("%02d", rank))
                    .setText(R.id.tv_year, "[$year]")
                    .setText(R.id.tv_movie, title)
                    .setText(R.id.tv_score, rating.average.toString())
                    .setRating(R.id.rb_score, (rating.average / 2f).toFloat())
                    .addTags(R.id.tag_genres, genres)
        }
    }
}