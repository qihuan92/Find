package com.qihuan.find.di.module;

import android.app.Application;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.qihuan.find.BuildConfig;
import com.qihuan.find.kit.NetKit;
import com.qihuan.find.model.net.api.DoubanApi;
import com.qihuan.find.model.net.api.ZhihuApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
 * NetworkModule
 * Created by Qi on 2017/9/27.
 */

@Module
public class NetworkModule {

    @Singleton
    @Provides
    CookiePersistor provideCookiePersistor(Application application) {
        return new SharedPrefsCookiePersistor(application);
    }

    @Singleton
    @Provides
    Cache provideCache(Application application) {
        // 10 MiB
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    Interceptor provideInterceptor() {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (NetKit.isConnected()) {
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

    @Singleton
    @Provides
    CookieJar provideCookieJar(CookiePersistor cookiePersistor) {
        return new PersistentCookieJar(new SetCookieCache(), cookiePersistor);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache, Interceptor interceptor, HttpLoggingInterceptor loggingInterceptor, CookieJar cookieJar) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        builder.addNetworkInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .cookieJar(cookieJar);
        return builder.build();
    }

    @Singleton
    @Provides
    ZhihuApi provideZhihuApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(ZhihuApi.ZHIHU_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ZhihuApi.class);


    }

    @Singleton
    @Provides
    DoubanApi provideDoubanApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(DoubanApi.DOUBAN_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DoubanApi.class);
    }
}
