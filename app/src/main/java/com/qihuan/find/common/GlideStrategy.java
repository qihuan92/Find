package com.qihuan.find.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.qihuan.imageloader.LoadStrategy;

/**
 * GlideStrategy
 * Created by Qi on 2017/9/18.
 */

public class GlideStrategy implements LoadStrategy {
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void load(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .transform(new RoundedCorners(radius))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static class Builder {
        private GlideStrategy glideStrategy;

        public Builder() {
            glideStrategy = new GlideStrategy();
        }

        public Builder radius(int radius) {
            glideStrategy.setRadius(radius);
            return this;
        }

        public GlideStrategy build() {
            return glideStrategy;
        }
    }
}
