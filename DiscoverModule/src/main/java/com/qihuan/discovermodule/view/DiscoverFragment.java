package com.qihuan.discovermodule.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qihuan.commonmodule.base.BaseFragment;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.discovermodule.R;

/**
 * DiscoverFragment
 *
 * @author Qi
 */
@Route(path = Router.DISCOVER_FRAGMENT)
public class DiscoverFragment extends BaseFragment {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

}
