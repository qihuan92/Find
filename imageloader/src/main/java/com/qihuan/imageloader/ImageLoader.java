package com.qihuan.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * ImageLoader
 * Created by Qi on 2017/9/18.
 */

public enum ImageLoader {
    INSTANCE;

    private LoadStrategy loadStrategy;
    private Context context;
    private String url;
    private ImageView imageView;

    public ImageLoader strategy(LoadStrategy loadStrategy) {
        this.loadStrategy = loadStrategy;
        return this;
    }

    public ImageLoader with(Context context) {
        this.context = context;
        return this;
    }

    public ImageLoader load(String url) {
        this.url = url;
        return this;
    }

    public ImageLoader into(ImageView imageView) {
        if (this.loadStrategy == null) {
            throw new IllegalStateException("LoadStrategy is null!");
        }
        if (imageView == null) {
            throw new IllegalStateException("ImageView is null!");
        }
        if (context == null) {
            throw new IllegalStateException("Context is null!");
        }
        this.imageView = imageView;
        loadStrategy.load(this.context, this.url, this.imageView);
        return this;
    }
}
