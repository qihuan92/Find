package com.qihuan.dailymodule.view

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpActivity
import com.qihuan.commonmodule.router.Routes
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
@Route(path = Routes.DAILY_DET_ACTIVITY)
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
        setToolBar(toolbar, color = android.R.color.transparent)

        fab_favorite.setOnClickListener { mPresenter.updateFavoriteStory(id) }

        web_view.apply {
            webChromeClient = WebChromeClient()
        }.settings.run {
            cacheMode = WebSettings.LOAD_NO_CACHE
            loadWithOverviewMode = true
            domStorageEnabled = true
            databaseEnabled = true
        }

        mPresenter.run {
            getStoryContent(id)
            getFavoriteStory(id)
        }
    }

    override fun storyContent(storyContent: StoryContentBean) {
        storyContent.run {
            if (body.isEmpty()) {
                web_view.loadUrl(share_url)
            } else {
                web_view.loadHtmlWithCss(body, css, false)
            }
            tv_title.text = title
            tv_copyright.text = image_source
            iv_daily.load(image)
        }
    }

    override fun storyExtra(storyExtra: StoryExtraBean) {

    }

    override fun onFavoriteChange(isFavorite: Boolean) {
        fab_favorite.setImageResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
    }

    override fun showUpdateFavoriteInfo(isFavorite: Boolean) {
        toastSuccess(if (isFavorite) "收藏成功" else "取消收藏成功")
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showError(errorMsg: String) {
        toastError(errorMsg)
    }

    override fun initPresenter(): DailyDetContract.Presenter {
        return DailyDetPresenter()
    }
}
