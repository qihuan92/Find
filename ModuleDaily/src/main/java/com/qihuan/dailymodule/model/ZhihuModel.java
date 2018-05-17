package com.qihuan.dailymodule.model;

import com.qihuan.commonmodule.net.ApiManager;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * ZhihuModel
 *
 * @author qi
 */
public class ZhihuModel {

    private ZhihuApi zhihuApi;

    public ZhihuApi getApi() {
        if (zhihuApi == null) {
            RetrofitUrlManager.getInstance().putDomain(ZhihuApi.DOMAIN_VALUE, ZhihuApi.BASE_URL);
            zhihuApi = ApiManager.getInstance().getRetrofit().create(ZhihuApi.class);
        }
        return zhihuApi;
    }
}
