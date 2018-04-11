package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * ImageLoader
 * Created by Qi on 2017/9/18.
 */

public enum ImageLoader {
    INSTANCE;

    private LoaderStrategy loaderStrategy;
    private Context context;
    private String url;
    private ImageView imageView;
    private LoaderOption option;

    public ImageLoader strategy(LoaderStrategy loaderStrategy) {
        this.loaderStrategy = loaderStrategy;
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

    public ImageLoader options(LoaderOption option) {
        this.option = option;
        return this;
    }

    public void into(ImageView target) {
        if (this.loaderStrategy == null) {
            throw new IllegalStateException("LoaderStrategy is null!");
        }
        if (target == null) {
            throw new IllegalStateException("ImageView is null!");
        }
        if (this.context == null) {
            throw new IllegalStateException("Context is null!");
        }
        this.imageView = target;
        loaderStrategy.load(this.context, this.url, this.imageView, this.option);
    }
}
