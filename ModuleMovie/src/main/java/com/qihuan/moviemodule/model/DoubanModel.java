package com.qihuan.moviemodule.model;

import com.qihuan.commonmodule.net.ApiManager;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class DoubanModel {

    private DoubanApi doubanApi;

    public DoubanApi getApi() {
        if (doubanApi == null) {
            RetrofitUrlManager.getInstance().putDomain(DoubanApi.DOMAIN_VALUE, DoubanApi.BASE_URL);
            doubanApi = ApiManager.getInstance().getRetrofit().create(DoubanApi.class);
        }
        return doubanApi;
    }
}
