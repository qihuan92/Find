package com.qihuan.commonmodule.net

import android.app.Application
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.qihuan.commonmodule.BuildConfig
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ApiManager
 *
 * @author Qi
 */
class ApiManager private constructor() {
    private val retrofit: Retrofit

    init {
        // RetrofitUrlManager 初始化
        val okHttpClient = RetrofitUrlManager.getInstance().with(client())
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(APP_DEFAULT_DOMAIN)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    fun <T> getApi(t: Class<T>): T {
        return retrofit.create(t)
    }

    /**
     * 在线缓存拦截器
     *
     * @return Interceptor
     */
    private fun onlineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            // 在线缓存在1分钟内可读取
            val maxAge = 60
            chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
        }
    }

    /**
     * 离线缓存拦截器
     *
     * @return Interceptor
     */
    private fun offlineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            // 离线时缓存保存1天
            val maxStale = 60 * 60 * 24
            chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
    }

    /**
     * 获取打印拦截器
     *
     * @return loggingInterceptor
     */
    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    /**
     * 获取缓存配置
     *
     * @return Cache
     */
    private fun cache(application: Application): Cache {
        // 10 MiB
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    /**
     * cookie 存储策略
     *
     * @return CookieJar
     */
    private fun cookieJar(application: Application): CookieJar {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(application))
    }

    /**
     * 获取 http client
     *
     * @return OkHttpClient
     */
    private fun client(): OkHttpClient.Builder {
        with(OkHttpClient.Builder()) {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor())
            }
            addNetworkInterceptor(onlineCacheInterceptor())
            addInterceptor(offlineCacheInterceptor())
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
            application?.let {
                cache(cache(it))
                cookieJar(cookieJar(it))
            }
            return this
        }
    }

    companion object {
        private const val APP_DEFAULT_DOMAIN = "https://qihuan92.github.io"
        private var application: Application? = null
        val instance: ApiManager by lazy { ApiManager() }

        fun init(application: Application) {
            ApiManager.application = application
        }
    }
}
