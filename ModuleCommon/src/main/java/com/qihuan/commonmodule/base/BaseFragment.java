package com.qihuan.commonmodule.base;

import android.support.v4.app.Fragment;

import com.qihuan.commonmodule.bus.BindEventBus;

import org.greenrobot.eventbus.EventBus;

/**
 * BaseFragment
 *
 * @author Qi
 * @date 2017/6/22
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
