package com.qihuan.moviemodule.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qihuan.commonmodule.utils.loadImage
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.PersonBean
import net.wujingchao.android.view.SimpleTagImageView

/**
 * MovieCardAdapter
 * @author qi
 * @date 2018/6/5
 */
class ActCardAdapter : BaseQuickAdapter<PersonBean, BaseViewHolder>(R.layout.item_act_card, null) {

    override fun convert(helper: BaseViewHolder, item: PersonBean?) {
        item?.apply {
            helper.loadImage(R.id.iv_act, item.avatars.medium)
                    .setText(R.id.tv_name, name)
            val ivAct = helper.getView(R.id.iv_act) as SimpleTagImageView
            ivAct.tagEnable = isDirector
        }
    }

}