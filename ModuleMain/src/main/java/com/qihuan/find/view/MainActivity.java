package com.qihuan.find.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qihuan.commonmodule.base.BaseActivity;
import com.qihuan.commonmodule.bus.BindEventBus;
import com.qihuan.commonmodule.bus.event.BrowserEvent;
import com.qihuan.commonmodule.bus.event.RefreshEvent;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.commonmodule.utils.AppUtils;
import com.qihuan.commonmodule.utils.NavigationBarUtils;
import com.qihuan.commonmodule.utils.StatusBarUtils;
import com.qihuan.commonmodule.utils.ToastUtils;
import com.qihuan.find.R;
import com.thefinestartist.finestwebview.FinestWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * MainActivity
 *
 * @author Qi
 */
@BindEventBus
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    private BottomNavigationView bottomView;
    private Fragment content = (Fragment) ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation();
    private Fragment dailyFragment = (Fragment) ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation();
    private Fragment movieFragment = (Fragment) ARouter.getInstance().build(Router.MOVIE_FRAGMENT).navigation();
    private Fragment discoverFragment = (Fragment) ARouter.getInstance().build(Router.DISCOVER_FRAGMENT).navigation();
    private Fragment meFragment = (Fragment) ARouter.getInstance().build(Router.USER_FRAGMENT).navigation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        UETool.showUETMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        UETool.dismissUETMenu();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    private void initView() {
        // 状态栏白色主题
        StatusBarUtils.statusBarLightMode(this);
        bottomView = findViewById(R.id.bottom_view);
        bottomView.setOnNavigationItemSelectedListener(this);
        bottomView.setOnNavigationItemReselectedListener(this);
        NavigationBarUtils.disableShiftMode(bottomView);
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
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bb_menu_daily:
                EventBus.getDefault().postSticky(new RefreshEvent());
                break;
            case R.id.bb_menu_movie:

                break;
            case R.id.bb_menu_discover:

                break;
            case R.id.bb_menu_me:

                break;
            default:
                break;
        }
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

    @Subscribe
    public void onBrowserEvent(BrowserEvent browserEvent) {
        if (browserEvent == null) {
            return;
        }
        FinestWebView.Builder webBuilder = new FinestWebView.Builder(getApplicationContext());
        if (!TextUtils.isEmpty(browserEvent.getTitle())) {
            webBuilder.titleDefault(browserEvent.getTitle());
        }
        if (TextUtils.isEmpty(browserEvent.getUrl())) {
            return;
        }
        webBuilder.show(browserEvent.getUrl());
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
