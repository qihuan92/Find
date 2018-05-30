package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * ImageLoaderConfig
 *
 * @author qi
 * @date 2018/5/29
 */
public class ImageLoaderConfig {
    private Context context;
    private ImageLoaderStrategy strategy;
    private String url;
    private ImageView target;
    private int radius;
    @DrawableRes
    private int placeHolder;

    public ImageLoaderConfig(@NonNull Context context, @NonNull ImageLoaderStrategy strategy) {
        this.context = context;
        this.strategy = strategy;
    }

    public ImageLoaderConfig url(String url) {
        this.url = url;
        return this;
    }

    public ImageLoaderConfig radius(int radius) {
        this.radius = radius;
        return this;
    }

    public ImageLoaderConfig placeHolder(int placeHolder) {
        this.placeHolder = placeHolder;
        return this;
    }

    public void into(ImageView target) {
        this.target = target;
        strategy.loadImage(this);
    }

    public Context getContext() {
        return context;
    }

    public ImageLoaderStrategy getStrategy() {
        return strategy;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getTarget() {
        return target;
    }

    public int getRadius() {
        return radius;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }
}
