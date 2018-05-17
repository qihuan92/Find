package com.qihuan.commonmodule.net;

import android.app.Application;
import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.commonmodule.BuildConfig;

import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiManager
 *
 * @author Qi
 */
public class ApiManager {

    private static final String APP_DEFAULT_DOMAIN = "https://qihuan92.github.io";
    private final Retrofit retrofit;
    private static Application application;

    public static void init(Application application) {
        ApiManager.application = application;
    }

    private static class ApiManagerHolder {
        private static final ApiManager INSTANCE = new ApiManager();
    }

    public static ApiManager getInstance() {
        return ApiManagerHolder.INSTANCE;
    }

    private ApiManager() {
        // RetrofitUrlManager 初始化
        OkHttpClient okHttpClient = RetrofitUrlManager.getInstance().with(client())
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(APP_DEFAULT_DOMAIN)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T getApi(Class<T> t) {
        return retrofit.create(t);
    }

    /**
     * 在线缓存拦截器
     *
     * @return Interceptor
     */
    private Interceptor onlineCacheInterceptor() {
        return chain -> {
            // 在线缓存在1分钟内可读取
            int maxAge = 60;
            return chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        };
    }

    /**
     * 离线缓存拦截器
     *
     * @return Interceptor
     */
    private Interceptor offlineCacheInterceptor() {
        return chain -> {
            // 离线时缓存保存1天
            int maxStale = 60 * 60 * 24;
            return chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        };
    }

    /**
     * 获取打印拦截器
     *
     * @return loggingInterceptor
     */
    private HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    /**
     * 获取缓存配置
     *
     * @return Cache
     */
    private Cache cache() {
        // 10 MiB
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    /**
     * cookie 存储策略
     *
     * @return CookieJar
     */
    private CookieJar cookieJar() {
        return new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application));
    }

    /**
     * 获取 http client
     *
     * @return OkHttpClient
     */
    private OkHttpClient.Builder client() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor());
        }
        builder.addNetworkInterceptor(onlineCacheInterceptor())
                .addInterceptor(offlineCacheInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache())
                .cookieJar(cookieJar());
        return builder;
    }


}
