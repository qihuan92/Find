package com.qihuan.find.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qihuan.find.R;
import com.qihuan.find.model.bean.zhihu.DailyItem;
import com.qihuan.find.model.bean.zhihu.StoriesEntity;
import com.qihuan.find.view.custom.weight.GlideRoundTransform;

import java.util.List;

/**
 * 知乎日报条目adapter
 * Created by Qi on 2017/7/12.
 */

public class DailyAdapter extends BaseSectionQuickAdapter<DailyItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DailyAdapter(List<DailyItem> data) {
        super(R.layout.item_daily, R.layout.item_date, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, DailyItem item) {
        helper.setText(R.id.tv_news_date, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyItem item) {
        StoriesEntity storiesEntity = item.t;
        helper.setText(R.id.tv_news, storiesEntity.getTitle());
        try {
            Glide.with(mContext)
                    .load(storiesEntity.getImages().get(0))
                    .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 2))
                    .crossFade()
                    .into((ImageView) helper.getView(R.id.iv_news));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
