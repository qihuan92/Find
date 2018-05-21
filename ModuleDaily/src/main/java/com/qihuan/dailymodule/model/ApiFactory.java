package com.qihuan.dailymodule.model;

import com.qihuan.commonmodule.net.ApiManager;

/**
 * ApiFactory
 *
 * @author qi
 * @date 2018/5/21
 */
public class ApiFactory {
    public static ZhihuApi getApi() {
        return ApiManager.getInstance().getApi(ZhihuApi.class);
    }
}
