package com.qihuan.find.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qihuan.find.R;
import com.qihuan.find.bean.zhihu.DailyEntity;
import com.qihuan.find.presenter.NewsPresenter;
import com.qihuan.find.view.base.BaseFragment;
import com.qihuan.find.view.i.INewsView;

import easymvp.annotation.FragmentView;
import easymvp.annotation.Presenter;

/**
 * NewsFragment
 */
@FragmentView(presenter = NewsPresenter.class)
public class NewsFragment extends BaseFragment implements INewsView {

    @Presenter
    NewsPresenter newsPresenter;

    private TextView tvTest;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTest = (TextView) view.findViewById(R.id.tv_test);
    }

    @Override
    public void onResume() {
        super.onResume();
        newsPresenter.load();
    }

    @Override
    public void s() {
        showProgressDialog();
    }

    @Override
    public void get(DailyEntity dailyEntity) {
        dismissProgressDialog();
//        tvTest.setText(dailyEntity.toString());
        tvTest.setText(dailyEntity.getTop_stories().get(0).getTitle());
    }

    @Override
    public void error(String message) {
        dismissProgressDialog();
    }
}
