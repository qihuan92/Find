package com.qihuan.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * LoaderStrategy
 * Created by Qi on 2017/9/18.
 */

public interface LoaderStrategy {
    void load(Context context, String url, ImageView target, LoaderOption options);
}
