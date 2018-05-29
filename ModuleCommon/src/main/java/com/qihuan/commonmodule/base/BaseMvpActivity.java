package com.qihuan.commonmodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * BaseMvpActivity
 *
 * @author qi
 * @date 2018/5/28
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            // noinspection unchecked
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    protected abstract P initPresenter();

}
