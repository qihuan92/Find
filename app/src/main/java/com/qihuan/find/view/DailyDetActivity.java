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
import com.qihuan.find.common.GlideApp;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.kit.WebKit;
import com.qihuan.find.view.base.BaseActivity;
import com.qihuan.find.viewmodel.DailyDetViewModel;


@Route(path = "/zhihu/det")
public class DailyDetActivity extends BaseActivity {

    @Autowired
    public int id;

    private Toolbar toolbar;
    private WebView webView;
    private TextView tvTitle;
    private FloatingActionButton fabFavorite;
    private ImageView ivDaily;
    private DailyDetViewModel dailyDetViewModel;
    private TextView tvCopyRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_det);
        ARouter.getInstance().inject(this);
        dailyDetViewModel = ViewModelProviders.of(this).get(DailyDetViewModel.class);
        dailyDetViewModel.storyContent.observe(this, storyContentBean -> {
            if (storyContentBean == null) {
                return;
            }
            String url = storyContentBean.getShare_url();
            if (TextUtils.isEmpty(storyContentBean.getBody())) {
                webView.loadUrl(url);
            } else {
                String data = WebKit.buildHtmlWithCss(storyContentBean.getBody(), storyContentBean.getCss(), false);
                webView.loadDataWithBaseURL(WebKit.BASE_URL, data, WebKit.MIME_TYPE, WebKit.ENCODING, WebKit.FAIL_URL);
            }
            tvTitle.setText(storyContentBean.getTitle());
            tvCopyRight.setText(storyContentBean.getImage_source());
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
                ToastKit.error(throwable);
            } else {
                ToastKit.warning(getString(R.string.net_error_msg));
                NetworkUtils.openWirelessSettings();
            }
        });
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);
        tvTitle = findViewById(R.id.tv_title);
        fabFavorite = findViewById(R.id.fab_favorite);
        ivDaily = findViewById(R.id.iv_daily);
        tvCopyRight = findViewById(R.id.tv_copyright);

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
