package com.qihuan.find.model.remote;

import android.app.Application;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.commonmodule.utils.NetUtils;
import com.qihuan.find.BuildConfig;
import com.qihuan.find.model.remote.api.DoubanApi;
import com.qihuan.find.model.remote.api.ZhihuApi;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient
 *
 * @author Qi
 */
public class RetrofitClient {

    private static Application application;

    public static void init(Application application) {
        RetrofitClient.application = application;
    }

    /**
     * 缓存拦截器
     *
     * @return Interceptor
     */
    private static Interceptor cacheInterceptor() {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (NetUtils.isConnected()) {
                // 在线缓存在1分钟内可读取
                int maxAge = 60;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                // 离线时缓存保存4周
                int maxStale = 60 * 60 * 24 * 28;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
    }

    /**
     * 获取打印拦截器
     *
     * @return loggingInterceptor
     */
    private static HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    /**
     * 获取缓存配置
     *
     * @return Cache
     */
    private static Cache cache() {
        // 10 MiB
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    /**
     * cookie 存储策略
     *
     * @return CookieJar
     */
    private static CookieJar cookieJar() {
        return new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application));
    }

    /**
     * 获取 http client
     *
     * @return OkHttpClient
     */
    private static OkHttpClient client() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor());
        }
        builder.addNetworkInterceptor(cacheInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache())
                .cookieJar(cookieJar());
        return builder.build();
    }

    public static ZhihuApi zhihuApi() {
        return new Retrofit.Builder()
                .baseUrl(ZhihuApi.ZHIHU_URL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ZhihuApi.class);
    }

    public static DoubanApi provideDoubanApi() {
        return new Retrofit.Builder()
                .baseUrl(DoubanApi.DOUBAN_URL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DoubanApi.class);
    }
}
