package com.qihuan.find.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.qihuan.find.R;
import com.qihuan.find.bean.zhihu.TopStoriesEntity;
import com.qihuan.find.view.custom.weight.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * DailyBannerAdapter
 * Created by Qi on 2017/7/13.
 */

public class DailyBannerAdapter extends PagerAdapter {

    private List<TopStoriesEntity> list = new ArrayList<>();
    private Context context;

    public DailyBannerAdapter(Context context, List<TopStoriesEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_banner, container, false);
        ImageView ivBanner = (ImageView) view.findViewById(R.id.iv_banner);
        Glide.with(context)
                .load(list.get(position).getImage())
                .transform(new CenterCrop(context), new GlideRoundTransform(context, 2))
                .crossFade()
                .into(ivBanner);
        container.addView(view);
        return view;
    }
}
