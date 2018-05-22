package com.qihuan.dailymodule.view.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qihuan.commonmodule.imageloader.GlideApp;
import com.qihuan.commonmodule.utils.SizeUtils;
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

    public DailyAdapter() {
        super(R.layout.item_daily, R.layout.item_date, null);
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
        helper.setVisible(R.id.pb_loading, true);
        GlideApp.with(mContext)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        helper.setVisible(R.id.pb_loading, false);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        helper.setVisible(R.id.pb_loading, false);
                        return false;
                    }
                })
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(SizeUtils.dp2px(4))))
                .into((ImageView) helper.getView(R.id.iv_news));
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
