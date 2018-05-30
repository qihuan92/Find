package com.qihuan.commonmodule.imageloader;

import android.content.Context;

/**
 * ImageLoader
 *
 * @author qi
 * @date 2018/5/29
 */
public class ImageLoader {
    private static ImageLoader instance;
    private ImageLoaderStrategy strategy;

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

    public void setStrategy(ImageLoaderStrategy strategy) {
        this.strategy = strategy;
    }

    public ImageLoaderConfig with(Context context) {
        return new ImageLoaderConfig(context, strategy);
    }
}
