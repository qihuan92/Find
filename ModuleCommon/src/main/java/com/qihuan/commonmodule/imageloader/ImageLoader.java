package com.qihuan.commonmodule.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * ImageLoader
 *
 * @author Qi
 * @date 2017/9/18
 */

public class ImageLoader {
    public static final int CENTER_CROP = 0;
    public static final int FIT_CENTER = 1;

    private static volatile ImageLoader instance;
    private static LoaderStrategy loaderStrategy;
    private WeakReference<Context> contextWeakReference;
    private String url;
    private int option;
    private OnImageLoadListener loadListener;
    @DrawableRes
    private int placeHolder;

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

    public static void init(LoaderStrategy loaderStrategy) {
        ImageLoader.loaderStrategy = loaderStrategy;
    }

    public ImageLoader with(Context context) {
        contextWeakReference = new WeakReference<>(context);
        return this;
    }

    public ImageLoader load(String url) {
        this.url = url;
        return this;
    }

    public ImageLoader listener(OnImageLoadListener loadListener) {
        this.loadListener = loadListener;
        return this;
    }

    public ImageLoader placeHolder(@DrawableRes int placeHolder) {
        this.placeHolder = placeHolder;
        return this;
    }

    public ImageLoader option(@LoaderOption int option) {
        this.option = option;
        return this;
    }

    public void into(ImageView target) {
        if (ImageLoader.loaderStrategy == null) {
            throw new IllegalStateException("LoaderStrategy is null!");
        }
        if (target == null) {
            throw new IllegalStateException("ImageView is null!");
        }
        Context context = contextWeakReference.get().getApplicationContext();
        if (context == null) {
            throw new IllegalStateException("Context is null!");
        }
        loaderStrategy.load(context, this.url, target, placeHolder, option, loadListener);
    }

    public static void trimMemory(int level) {
        ImageLoader.loaderStrategy.trimMemory(level);
    }

    public static void clearAllMemoryCaches() {
        ImageLoader.loaderStrategy.clearAllMemoryCaches();
    }

    public static void clearDiskCache() {
        ImageLoader.loaderStrategy.clearDiskCache();
    }

    public static void clearMomory() {
        ImageLoader.loaderStrategy.clearMemory();
    }

    public interface OnImageLoadListener {
        void onStart();

        void onFinish(boolean isSuccess);
    }

    @IntDef({CENTER_CROP, FIT_CENTER})
    @interface LoaderOption {
    }
}
