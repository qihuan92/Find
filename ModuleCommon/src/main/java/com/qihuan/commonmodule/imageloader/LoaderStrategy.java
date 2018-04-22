package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * LoaderStrategy
 *
 * @author Qi
 * @date 2017/9/18
 */

public interface LoaderStrategy {
    void load(Context context, String url, ImageView target, @DrawableRes int placeHolder, @ImageLoader.LoaderOption int option, ImageLoader.OnImageLoadListener loadListener);
}
