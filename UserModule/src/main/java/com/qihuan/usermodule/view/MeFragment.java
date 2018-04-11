package com.qihuan.usermodule.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qihuan.commonmodule.base.BaseFragment;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.usermodule.R;

/**
 * MeFragment
 *
 * @author Qi
 */
@Route(path = Router.USER_FRAGMENT)
public class MeFragment extends BaseFragment {

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

}
