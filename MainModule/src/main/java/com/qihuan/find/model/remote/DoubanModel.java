package com.qihuan.find.model.remote;

import com.qihuan.commonmodule.net.ApiClient;
import com.qihuan.find.model.remote.api.DoubanApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoubanModel {

    public DoubanApi getApi() {
        return new Retrofit.Builder()
            .baseUrl(DoubanApi.DOUBAN_URL)
            .client(ApiClient.client())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DoubanApi.class);
    }
}
