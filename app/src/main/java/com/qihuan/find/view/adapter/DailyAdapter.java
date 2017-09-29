package com.qihuan.find.view.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qihuan.find.R;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.StoryBean;
import com.qihuan.find.model.bean.zhihu.StoryExtraBean;
import com.qihuan.imageloader.ImageLoader;
import com.qihuan.imageloader.LoaderStrategy;

import java.util.List;

/**
 * 知乎日报条目adapter
 * Created by Qi on 2017/7/12.
 */

public class DailyAdapter extends BaseSectionQuickAdapter<DailyItemBean, BaseViewHolder> {

    private LoaderStrategy loaderStrategy;

    public DailyAdapter(LoaderStrategy loaderStrategy) {
        super(R.layout.item_daily, R.layout.item_date, null);
        this.loaderStrategy = loaderStrategy;
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
        ImageLoader.INSTANCE
                .strategy(loaderStrategy)
                .with(mContext)
                .load(url)
                .options(() -> 10)
                .into(helper.getView(R.id.iv_news));
        // set extra
        StoryExtraBean extra = storyBean.getStoryExtraBean();
        helper.setText(R.id.tv_like, String.valueOf(extra.getPopularity()));
        helper.setText(R.id.tv_comment, String.valueOf(extra.getComments()));
    }

    public void clear() {
        mData.clear();
    }
}
