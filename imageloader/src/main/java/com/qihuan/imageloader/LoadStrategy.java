package com.qihuan.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * LoadStrategy
 * Created by Qi on 2017/9/18.
 */

public interface LoadStrategy {
    void load(Context context, String url, ImageView imageView);
}
