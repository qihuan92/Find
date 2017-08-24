package com.qihuan.find.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.qihuan.find.R;
import com.qihuan.find.config.GlideApp;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.kit.WebKit;
import com.qihuan.find.model.bean.zhihu.StoryContentEntity;
import com.qihuan.find.model.bean.zhihu.StoryExtraEntity;
import com.qihuan.find.presenter.DailyDetPresenter;
import com.qihuan.find.view.base.BaseActivity;
import com.qihuan.find.view.i.IDailyDetView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.concurrent.TimeUnit;

import easymvp.annotation.ActivityView;
import easymvp.annotation.Presenter;
import io.reactivex.Observable;

@Route(path = "/zhihu/det")
@ActivityView(presenter = DailyDetPresenter.class)
public class DailyDetActivity extends BaseActivity implements IDailyDetView {

    @Presenter
    DailyDetPresenter dailyDetPresenter;

    @Autowired
    public int id;

    private Toolbar toolbar;
    private WebView webView;
    private CollapsingToolbarLayout clpToolbar;
    private TextView tvCopyright;
    private FloatingActionButton fabFavorite;
    private ImageView ivDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_det);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);
        clpToolbar = findViewById(R.id.clp_toolbar);
        tvCopyright = findViewById(R.id.tv_copyright);
        fabFavorite = findViewById(R.id.fab_favorite);
        ivDaily = findViewById(R.id.iv_daily);

        setToolBar(toolbar, "");

        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> dailyDetPresenter.getStoryContent(id));
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void error(String throwable) {
        if (NetworkUtils.isConnected()) {
            ToastKit.error(throwable);
        } else {
            ToastKit.warning("请连接网络");
            NetworkUtils.openWirelessSettings();
        }
    }

    @Override
    public void storyContent(StoryContentEntity storyContentEntity) {
        String url = storyContentEntity.getShare_url();
        if (TextUtils.isEmpty(storyContentEntity.getBody())) {
            webView.loadUrl(url);
        } else {
            String data = WebKit.buildHtmlWithCss(storyContentEntity.getBody(), storyContentEntity.getCss(), false);
            webView.loadDataWithBaseURL(WebKit.BASE_URL, data, WebKit.MIME_TYPE, WebKit.ENCODING, WebKit.FAIL_URL);
        }
        clpToolbar.setTitle(storyContentEntity.getTitle());
        tvCopyright.setText(storyContentEntity.getImage_source());
        GlideApp.with(this)
                .load(storyContentEntity.getImage())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivDaily);
    }

    @Override
    public void storyExtra(StoryExtraEntity storyExtraEntity) {

    }
}
