package com.qihuan.commonmodule.imageloader.glide;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.qihuan.commonmodule.imageloader.ImageLoaderConfig;
import com.qihuan.commonmodule.imageloader.ImageLoaderStrategy;
import com.qihuan.commonmodule.utils.SizeUtils;

/**
 * GlideStrategy
 *
 * @author qi
 * @date 2018/5/29
 */
public class GlideStrategy implements ImageLoaderStrategy {

    @Override
    public void loadImage(ImageLoaderConfig config) {

        if (config.getPlaceHolder() == 0) {
            config.placeHolder(android.R.color.transparent);
        }

        if (config.getRadius() == 0) {
            GlideApp.with(config.getContext())
                    .load(config.getUrl())
                    .centerCrop()
                    .placeholder(config.getPlaceHolder())
                    .into(config.getTarget());
            return;
        }

        GlideApp.with(config.getContext())
                .load(config.getUrl())
                .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(SizeUtils.dp2px(config.getRadius()))))
                .placeholder(config.getPlaceHolder())
                .into(config.getTarget());
    }
}
