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
import com.qihuan.themeloader.ColorPickerDialog;
import com.qihuan.themeloader.Colorful;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
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

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        bottomView = findViewById(R.id.bottom_view);
        bottomView.setOnNavigationItemSelectedListener(this);
        switchContent(dailyFragment);
        toolbar.setTitle(R.string.title_news);
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

                ColorPickerDialog dialog = new ColorPickerDialog(this);
                dialog.setOnColorSelectedListener(color -> {
                    Colorful.config(this)
                            .primaryColor(color)
                            .accentColor(color)
                            .translucent(true)
                            .dark(false)
                            .apply();
                });
                dialog.show();
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
            ToastKit.info(getString(R.string.double_click_exit));
        }
    }

}
