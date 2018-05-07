package com.qihuan.dailymodule.view;

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
import com.qihuan.commonmodule.base.BaseActivity;
import com.qihuan.commonmodule.imageloader.ImageLoader;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.commonmodule.utils.ToastUtils;
import com.qihuan.commonmodule.utils.WebUtils;
import com.qihuan.dailymodule.R;
import com.qihuan.dailymodule.contract.DailyDetContract;
import com.qihuan.dailymodule.model.bean.StoryContentBean;
import com.qihuan.dailymodule.model.bean.StoryExtraBean;
import com.qihuan.dailymodule.presenter.DailyDetPresenter;

/**
 * DailyDetActivity
 *
 * @author Qi
 */
@Route(path = Router.DAILY_DET_ACTIVITY)
public class DailyDetActivity extends BaseActivity implements DailyDetContract.View {

    private Toolbar toolbar;
    private WebView webView;
    private TextView tvTitle;
    private FloatingActionButton fabFavorite;
    private ImageView ivDaily;
    private TextView tvCopyRight;
    private DailyDetPresenter presenter;

    @Autowired
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_det);
        ARouter.getInstance().inject(this);
        presenter = new DailyDetPresenter();
        presenter.attachView(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);
        tvTitle = findViewById(R.id.tv_title);
        fabFavorite = findViewById(R.id.fab_favorite);
        ivDaily = findViewById(R.id.iv_daily);
        tvCopyRight = findViewById(R.id.tv_copyright);

        fabFavorite.setOnClickListener(view -> onFavoriteClick());

        setToolBar(toolbar, "");

        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        presenter.getStoryContent(id);
        presenter.getFavoriteStory(id);
    }

    /**
     * 收藏
     */
    private void onFavoriteClick() {
        presenter.updateFavoriteStory(id);
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
    public void storyContent(StoryContentBean storyContent) {
        String url = storyContent.getShare_url();
        if (TextUtils.isEmpty(storyContent.getBody())) {
            webView.loadUrl(url);
        } else {
            String data = WebUtils.buildHtmlWithCss(storyContent.getBody(), storyContent.getCss(), false);
            webView.loadDataWithBaseURL(WebUtils.BASE_URL, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, WebUtils.FAIL_URL);
        }
        tvTitle.setText(storyContent.getTitle());
        tvCopyRight.setText(storyContent.getImage_source());
        ImageLoader.getInstance()
            .with(this)
            .load(storyContent.getImage())
            .into(ivDaily);
    }

    @Override
    public void storyExtra(StoryExtraBean storyExtra) {

    }

    @Override
    public void onFavoriteChange(boolean isFavorite) {
        if (isFavorite) {
            fabFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    @Override
    public void showUpdateFavoriteInfo(boolean isFavorite) {
        ToastUtils.success(isFavorite ? "收藏成功" : "取消收藏成功");
    }

    @Override
    public void showLoading() {
        showProgressLoading();
    }

    @Override
    public void hideLoading() {
        hideProgressLoading();
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtils.error(errorMsg);
    }
}
