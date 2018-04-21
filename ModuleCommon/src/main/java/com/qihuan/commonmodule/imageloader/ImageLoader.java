package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * ImageLoader
 *
 * @author Qi
 * @date 2017/9/18
 */

public class ImageLoader {
    private static volatile ImageLoader instance;
    private LoaderStrategy loaderStrategy;
    private WeakReference<Context> contextWeakReference;
    private String url;
    private LoaderOption option;

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public ImageLoader strategy(LoaderStrategy loaderStrategy) {
        this.loaderStrategy = loaderStrategy;
        return this;
    }

    public ImageLoader with(Context context) {
        contextWeakReference = new WeakReference<>(context);
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
        Context context = contextWeakReference.get();
        if (context == null) {
            throw new IllegalStateException("Context is null!");
        }
        loaderStrategy.load(context, this.url, target, this.option);
    }
}
