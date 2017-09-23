package com.qihuan.find.model.net;


import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.qihuan.find.App;
import com.qihuan.find.BuildConfig;
import com.qihuan.find.common.AppConfig;
import com.qihuan.find.kit.AppKit;
import com.qihuan.find.model.net.api.DoubanApi;
import com.qihuan.find.model.net.api.ZhihuApi;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Client
 * Created by Qi on 2016/9/27.
 */

public class Client {
    private static ZhihuApi zhihuApi = null;
    private static DoubanApi doubanApi = null;
    private static File httpCacheDirectory = new File(AppKit.getContext().getCacheDir(), AppConfig.CACHE_NAME);
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private static final CacheRewriteInterceptor INTERCEPTOR = new CacheRewriteInterceptor();

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        builder.addNetworkInterceptor(INTERCEPTOR)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), App.getSharedPrefsCookiePersistor()));
        return builder.build();
    }

    public static ZhihuApi getZhihuApi() {
        synchronized (Client.class) {
            if (zhihuApi == null) {
                zhihuApi = new Retrofit.Builder()
                        .baseUrl(ZhihuApi.ZHIHU_URL)
                        .client(getClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(ZhihuApi.class);
            }
            return zhihuApi;
        }
    }

    public static DoubanApi getDoubanApi() {
        synchronized (Client.class) {
            if (doubanApi == null) {
                doubanApi = new Retrofit.Builder()
                        .baseUrl(DoubanApi.DOUBAN_URL)
                        .client(getClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build().create(DoubanApi.class);
            }
            return doubanApi;
        }
    }

}
