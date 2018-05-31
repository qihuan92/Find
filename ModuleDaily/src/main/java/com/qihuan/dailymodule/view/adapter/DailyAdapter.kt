package com.qihuan.dailymodule.view.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qihuan.commonmodule.utils.loadImage
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.model.bean.DailyItemBean

/**
 * 知乎日报条目adapter
 *
 * @author Qi
 * @date 2017/7/12
 */

class DailyAdapter : BaseSectionQuickAdapter<DailyItemBean, BaseViewHolder>(R.layout.item_daily, R.layout.item_date, null) {

    override fun convertHead(helper: BaseViewHolder, item: DailyItemBean) {
        helper.setText(R.id.tv_news_date, item.header)
    }

    override fun convert(helper: BaseViewHolder, item: DailyItemBean) {
        with(item.t) {
            val url = if (images.isNotEmpty()) images[0] else ""
            helper.setText(R.id.tv_news, title)
                    .setText(R.id.tv_like, storyExtraBean.popularity.toString())
                    .setText(R.id.tv_comment, storyExtraBean.comments.toString())
                    .loadImage(R.id.iv_news, url, 4f)
        }
    }
}
