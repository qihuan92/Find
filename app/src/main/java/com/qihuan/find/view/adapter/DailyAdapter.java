package com.qihuan.find.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qihuan.find.R;
import com.qihuan.find.config.GlideApp;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.StoryBean;

import java.util.List;

/**
 * 知乎日报条目adapter
 * Created by Qi on 2017/7/12.
 */

public class DailyAdapter extends BaseSectionQuickAdapter<DailyItemBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DailyAdapter(List<DailyItemBean> data) {
        super(R.layout.item_daily, R.layout.item_date, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, DailyItemBean item) {
        helper.setText(R.id.tv_news_date, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyItemBean item) {
        StoryBean storyBean = item.t;
        helper.setText(R.id.tv_news, storyBean.getTitle());
        try {
            GlideApp.with(mContext)
                    .load(storyBean.getImages().get(0))
                    .transform(new RoundedCorners(10))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.iv_news));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
