package com.qihuan.find.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.qihuan.imageloader.LoaderStrategy;
import com.qihuan.imageloader.LoaderOption;

/**
 * GlideStrategy
 * Created by Qi on 2017/9/18.
 */

public class GlideStrategy implements LoaderStrategy {

    @Override
    public void load(Context context, String url, ImageView target, LoaderOption option) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.transform(new RoundedCorners(option.radius()));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(target);
    }

}
