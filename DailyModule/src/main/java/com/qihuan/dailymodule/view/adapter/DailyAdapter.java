package com.qihuan.dailymodule.view.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qihuan.commonmodule.imageloader.ImageLoader;
import com.qihuan.commonmodule.imageloader.LoaderStrategy;
import com.qihuan.commonmodule.imageloader.strategy.GlideStrategy;
import com.qihuan.dailymodule.R;
import com.qihuan.dailymodule.model.bean.DailyItemBean;
import com.qihuan.dailymodule.model.bean.StoryBean;
import com.qihuan.dailymodule.model.bean.StoryExtraBean;

import java.util.List;

/**
 * 知乎日报条目adapter
 *
 * @author Qi
 * @date 2017/7/12
 */

public class DailyAdapter extends BaseSectionQuickAdapter<DailyItemBean, BaseViewHolder> {

    private LoaderStrategy loaderStrategy;

    public DailyAdapter() {
        super(R.layout.item_daily, R.layout.item_date, null);
        this.loaderStrategy = new GlideStrategy();
    }

    @Override
    protected void convertHead(BaseViewHolder helper, DailyItemBean item) {
        helper.setText(R.id.tv_news_date, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyItemBean item) {
        StoryBean storyBean = item.t;
        List<String> images = storyBean.getImages();
        String url = null;
        if (images.size() != 0) {
            url = images.get(0);
        }
        // set title
        helper.setText(R.id.tv_news, storyBean.getTitle());
        // load image
        ImageLoader.getInstance()
            .strategy(loaderStrategy)
            .with(mContext)
            .load(url)
            .options(() -> 10)
            .into(helper.getView(R.id.iv_news));
        // set extra
        StoryExtraBean extra = storyBean.getStoryExtraBean();
        if (extra == null) {
            helper.setVisible(R.id.tv_like, false);
            helper.setVisible(R.id.tv_comment, false);
            return;
        }
        helper.setText(R.id.tv_like, String.valueOf(extra.getPopularity()));
        helper.setText(R.id.tv_comment, String.valueOf(extra.getComments()));
    }
}
