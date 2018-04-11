package com.qihuan.commonmodule.base;

import android.app.Application;

import com.qihuan.commonmodule.collection.bean.MyObjectBox;
import com.qihuan.commonmodule.net.ApiClient;
import com.qihuan.commonmodule.utils.AppUtils;
import com.qihuan.commonmodule.utils.ToastUtils;

import io.objectbox.BoxStore;

/**
 * BaseApp
 *
 * @author Qi
 */
public class BaseApp extends Application {

    private static BaseApp app;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
    }

    public static BaseApp getInstance() {
        return app;
    }

    private void init() {
        ApiClient.init(this);
        AppUtils.init(this);
        ToastUtils.init(this);

        boxStore = MyObjectBox.builder()
            .androidContext(this)
            .build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
