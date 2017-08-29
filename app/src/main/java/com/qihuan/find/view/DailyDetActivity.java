package com.qihuan.find.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
import com.qihuan.find.view.base.BaseActivity;
import com.qihuan.find.viewmodel.DailyDetViewModel;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

@Route(path = "/zhihu/det")
public class DailyDetActivity extends BaseActivity {

    @Autowired
    public int id;

    private Toolbar toolbar;
    private WebView webView;
    private CollapsingToolbarLayout clpToolbar;
    private TextView tvCopyright;
    private FloatingActionButton fabFavorite;
    private ImageView ivDaily;
    private DailyDetViewModel dailyDetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_det);
        ARouter.getInstance().inject(this);
        dailyDetViewModel = ViewModelProviders.of(this).get(DailyDetViewModel.class);
        dailyDetViewModel.storyContent.observe(this, storyContentBean -> {
            String url = storyContentBean.getShare_url();
            if (TextUtils.isEmpty(storyContentBean.getBody())) {
                webView.loadUrl(url);
            } else {
                String data = WebKit.buildHtmlWithCss(storyContentBean.getBody(), storyContentBean.getCss(), false);
                webView.loadDataWithBaseURL(WebKit.BASE_URL, data, WebKit.MIME_TYPE, WebKit.ENCODING, WebKit.FAIL_URL);
            }
            clpToolbar.setTitle(storyContentBean.getTitle());
            tvCopyright.setText(storyContentBean.getImage_source());
            GlideApp.with(this)
                    .load(storyContentBean.getImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivDaily);
        });
        dailyDetViewModel.storyExtra.observe(this, storyExtraBean -> {

        });
        dailyDetViewModel.error.observe(this, throwable -> {
            if (NetworkUtils.isConnected()) {
                ToastKit.error(throwable.getMessage());
            } else {
                ToastKit.warning("请连接网络");
                NetworkUtils.openWirelessSettings();
            }
        });
        dailyDetViewModel.complete.observe(this, aVoid -> {

        });
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

        dailyDetViewModel.getStoryContent(id);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
