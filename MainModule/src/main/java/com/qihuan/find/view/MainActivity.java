package com.qihuan.find.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.qihuan.commonmodule.utils.AppUtils;
import com.qihuan.commonmodule.utils.ToastUtils;
import com.qihuan.find.R;
import com.qihuan.find.view.base.BaseActivity;
import com.qihuan.find.view.daily.DailyFragment;
import com.qihuan.find.view.discover.DiscoverFragment;
import com.qihuan.find.view.me.MeFragment;
import com.qihuan.find.view.movie.MovieFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomView;
    private Fragment content = DailyFragment.newInstance();
    private DailyFragment dailyFragment = DailyFragment.newInstance();
    private MovieFragment movieFragment = MovieFragment.newInstance();
    private DiscoverFragment discoverFragment = DiscoverFragment.newInstance();
    private MeFragment meFragment = MeFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    private void initView() {
        bottomView = findViewById(R.id.bottom_view);
        bottomView.setOnNavigationItemSelectedListener(this);
        switchContent(dailyFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bb_menu_daily:
                switchContent(dailyFragment);
                break;
            case R.id.bb_menu_movie:
                switchContent(movieFragment);
                break;
            case R.id.bb_menu_discover:
                switchContent(discoverFragment);
                break;
            case R.id.bb_menu_me:
                switchContent(meFragment);
                break;
            default:
                break;
        }
//        toolbar.setTitle(item.getTitle());
        return true;
    }

    private void switchContent(Fragment fragment) {
        if (content != fragment) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(content).show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(content).add(R.id.fl_content, fragment).commit();
            }
        }
        content = fragment;
    }

    @Override
    public void onBackPressed() {
        if (AppUtils.isFastClick()) {
            super.onBackPressed();
        } else {
            ToastUtils.info(getString(R.string.double_click_exit));
        }
    }

}
