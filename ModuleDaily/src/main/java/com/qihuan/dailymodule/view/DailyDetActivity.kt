package com.qihuan.dailymodule.view

import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpActivity
import com.qihuan.commonmodule.router.Router
import com.qihuan.commonmodule.utils.load
import com.qihuan.commonmodule.utils.loadHtmlWithCss
import com.qihuan.commonmodule.utils.toastError
import com.qihuan.commonmodule.utils.toastSuccess
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.contract.DailyDetContract
import com.qihuan.dailymodule.model.bean.StoryContentBean
import com.qihuan.dailymodule.model.bean.StoryExtraBean
import com.qihuan.dailymodule.presenter.DailyDetPresenter
import kotlinx.android.synthetic.main.activity_daily_det.*

/**
 * DailyDetActivity
 *
 * @author Qi
 */
@Route(path = Router.DAILY_DET_ACTIVITY)
class DailyDetActivity : BaseMvpActivity<DailyDetContract.View, DailyDetContract.Presenter>(), DailyDetContract.View {
    @JvmField
    @Autowired
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_det)
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {
        setToolBar(toolbar, "")

        fab_favorite.setOnClickListener { onFavoriteClick() }

        val settings = web_view.settings
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        web_view.webChromeClient = WebChromeClient()

        mPresenter.getStoryContent(id)
        mPresenter.getFavoriteStory(id)
    }

    /**
     * 收藏
     */
    private fun onFavoriteClick() {
        mPresenter.updateFavoriteStory(id)
    }

    override fun storyContent(storyContent: StoryContentBean) {
        val url = storyContent.share_url
        if (TextUtils.isEmpty(storyContent.body)) {
            web_view.loadUrl(url)
        } else {
            web_view.loadHtmlWithCss(storyContent.body, storyContent.css, false)
        }
        tv_title.text = storyContent.title
        tv_copyright.text = storyContent.image_source
        iv_daily.load(storyContent.image)
    }

    override fun storyExtra(storyExtra: StoryExtraBean) {

    }

    override fun onFavoriteChange(isFavorite: Boolean) {
        if (isFavorite) {
            fab_favorite.setImageResource(R.drawable.ic_favorite)
        } else {
            fab_favorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    override fun showUpdateFavoriteInfo(isFavorite: Boolean) {
        toastSuccess(if (isFavorite) "收藏成功" else "取消收藏成功")
    }

    override fun showLoading() {
        showProgressLoading()
    }

    override fun hideLoading() {
        hideProgressLoading()
    }

    override fun showError(errorMsg: String) {
        toastError(errorMsg)
    }

    override fun initPresenter(): DailyDetContract.Presenter {
        return DailyDetPresenter()
    }
}
