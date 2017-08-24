package com.qihuan.find.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qihuan.find.R;
import com.qihuan.find.kit.AppKit;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.view.base.BaseActivity;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BottomNavigationView bottomView;
    private Fragment content = NewsFragment.newInstance();
    private NewsFragment newsFragment = NewsFragment.newInstance();
    private MovieFragment movieFragment = MovieFragment.newInstance();
    private DiscoverFragment discoverFragment = DiscoverFragment.newInstance();
    private MeFragment meFragment = MeFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        bottomView = findViewById(R.id.bottom_view);
        bottomView.setOnNavigationItemSelectedListener(this);
        switchContent(newsFragment);
        toolbar.setTitle(R.string.title_news);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bb_menu_news:
                switchContent(newsFragment);
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
        }
        toolbar.setTitle(item.getTitle());
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
        if (AppKit.isFastClick()) {
            super.onBackPressed();
        } else {
            ToastKit.info("再按一次退出应用");
        }
    }

}
