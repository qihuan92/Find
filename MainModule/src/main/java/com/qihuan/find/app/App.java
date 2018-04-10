package com.qihuan.find.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qihuan.commonmodule.utils.AppUtils;
import com.qihuan.commonmodule.utils.ToastUtils;
import com.qihuan.find.model.bean.find.MyObjectBox;
import com.qihuan.commonmodule.net.ApiClient;

import io.objectbox.BoxStore;

/**
 * App
 * Created by Qi on 2017/6/20.
 */

public class App extends Application {

    private static App app;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
    }

    public static App getInstance() {
        return app;
    }

    private void init() {
        ApiClient.init(this);
        AppUtils.init(this);
        ARouter.init(this);
        ToastUtils.init(this);

        boxStore = MyObjectBox.builder()
                .androidContext(this)
                .build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

}
