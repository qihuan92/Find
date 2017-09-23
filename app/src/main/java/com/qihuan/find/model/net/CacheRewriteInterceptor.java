package com.qihuan.find.model.net;

import android.support.annotation.NonNull;

import com.qihuan.find.kit.NetKit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * CacheRewriteInterceptor
 * Created by Qi on 2017/9/23.
 */

public class CacheRewriteInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
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
    }
}
