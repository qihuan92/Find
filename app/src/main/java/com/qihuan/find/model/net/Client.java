package com.qihuan.find.model.net;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.qihuan.find.App;
import com.qihuan.find.BuildConfig;
import com.qihuan.find.config.AppConfig;
import com.qihuan.find.model.net.api.DoubanApi;
import com.qihuan.find.model.net.api.ZhihuApi;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
    private static final Object monitor = new Object();
    private static File httpCacheDirectory = new File(Utils.getContext().getCacheDir(), "FindCache");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request());
        if (NetworkUtils.isAvailableByPing()) {
            int maxAge = 60; // 在线缓存在1分钟内可读取
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;// 离线时缓存保存4周
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };


    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        builder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), App.getSharedPrefsCookiePersistor()));
        return builder.build();
    }

    public static ZhihuApi getZhihuApi() {
        synchronized (monitor) {
            if (zhihuApi == null) {
                zhihuApi = new Retrofit.Builder()
                        .baseUrl(AppConfig.ZHIHU_URL)
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
        synchronized (monitor) {
            if (doubanApi == null) {
                doubanApi = new Retrofit.Builder()
                        .baseUrl(AppConfig.DOUBAN_URL)
                        .client(getClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build().create(DoubanApi.class);
            }
            return doubanApi;
        }
    }

}
