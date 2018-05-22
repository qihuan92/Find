package com.qihuan.commonmodule.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qihuan.commonmodule.BuildConfig;
import com.qihuan.commonmodule.collection.bean.MyObjectBox;
import com.qihuan.commonmodule.net.ApiManager;
import com.qihuan.commonmodule.utils.AppUtils;
import com.qihuan.commonmodule.utils.ToastUtils;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import io.objectbox.BoxStore;

/**
 * BaseApp
 *
 * @author Qi
 */
public class BaseApp extends Application {

    private static BaseApp app;
    private BoxStore boxStore;

    static {
        // 设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            // 指定为经典Header，默认是 贝塞尔雷达Header
            return new DeliveryHeader(context);
        });
        // 设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            // 指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

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
        ApiManager.init(this);
        AppUtils.init(this);
        ToastUtils.init(this);
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);

        boxStore = MyObjectBox.builder()
                .androidContext(this)
                .build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
