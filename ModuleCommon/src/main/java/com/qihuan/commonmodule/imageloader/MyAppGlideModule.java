package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * MyAppGlideModule
 *
 * @author qi
 */
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {

    private static final int DISK_SIZE = 1024 * 1024 * 100;
    /**
     * 取1/8最大内存作为最大缓存
     */
    private int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;

    @Override
    public void applyOptions(@NonNull Context context, GlideBuilder builder) {
        // 定义缓存大小和位置
        // 内存中
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_SIZE));
        // sd卡中
        // builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", DISK_SIZE));

        // 默认内存和图片池大小
        // MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        // int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
        // int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
        // builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize));
        // builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));

        // 自定义内存和图片池大小
        // 自定义内存大小
        builder.setMemoryCache(new LruResourceCache(memorySize));
        // 自定义图片池大小
        builder.setBitmapPool(new LruBitmapPool(memorySize));

        // glide4.x默认图片质量为ARGB_8888,这里设置为565节约内存
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
    }

    /**
     * 禁用清单解析
     * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
     *
     * @return bool
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}