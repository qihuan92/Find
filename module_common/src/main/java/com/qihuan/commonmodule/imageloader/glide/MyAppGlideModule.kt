package com.qihuan.commonmodule.imageloader.glide

import android.content.Context

import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 * MyAppGlideModule
 *
 * @author qi
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    /**
     * 取1/8最大内存作为最大缓存
     */
    private val memorySize = Runtime.getRuntime().maxMemory().toInt() / 8

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // 定义缓存大小和位置
        // 内存中
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, DISK_SIZE.toLong()))
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
        builder.setMemoryCache(LruResourceCache(memorySize.toLong()))
        // 自定义图片池大小
        builder.setBitmapPool(LruBitmapPool(memorySize.toLong()))

        // glide4.x默认图片质量为ARGB_8888,这里设置为565节约内存
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
    }

    /**
     * 禁用清单解析
     * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
     *
     * @return bool
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    companion object {

        private const val DISK_SIZE = 1024 * 1024 * 100
    }

}