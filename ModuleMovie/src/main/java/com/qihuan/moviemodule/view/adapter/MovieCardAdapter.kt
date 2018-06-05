package com.qihuan.moviemodule.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qihuan.commonmodule.utils.loadImage
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.SubjectBean

/**
 * MovieCardAdapter
 * @author qi
 * @date 2018/6/5
 */
class MovieCardAdapter : BaseQuickAdapter<SubjectBean, BaseViewHolder>(R.layout.item_movie_card, null) {

    override fun convert(helper: BaseViewHolder, item: SubjectBean?) {
        item?.apply {
            helper.loadImage(R.id.iv_movie, images.medium)
                    .setText(R.id.tv_movie, title)
                    .setText(R.id.tv_score, rating.average.toString())
                    .setRating(R.id.rb_score, (rating.average / 2f).toFloat())
        }
    }

}