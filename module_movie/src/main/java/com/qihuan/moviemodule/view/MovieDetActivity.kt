package com.qihuan.moviemodule.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvvmActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.*
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.viewmodel.MovieDetViewModel
import kotlinx.android.synthetic.main.activity_movie_det.*
import kotlinx.android.synthetic.main.include_movie_title.*
import kotlinx.android.synthetic.main.item_act_card.view.*
import zlc.season.yaksa.linear

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_DET_ACTIVITY)
class MovieDetActivity : BaseMvvmActivity<MovieDetViewModel>(MovieDetViewModel::class.java) {

    @JvmField
    @Autowired
    var id: String = ""

    private var isSummaryExpend = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_det)
        ARouter.getInstance().inject(this)
        initView()
        mViewModel.getSubject(id)
        mViewModel.getFavoriteMovie(id)
    }

    private fun initView() {
        setToolBar(toolbar, color = android.R.color.transparent)
        fab_favorite.setOnClickListener { mViewModel.updateFavoriteMovie(id) }

        mViewModel.bindUIState(this) {
            when (it) {
                MovieDetViewModel.UIState.LOADING -> showLoadingDialog()
                MovieDetViewModel.UIState.ERROR -> {
                    hideLoadingDialog()
                    toastError()
                }
                MovieDetViewModel.UIState.FINISH -> hideLoadingDialog()
            }
        }

        mViewModel.bindMovieData(this) { subjectBean ->
            subjectBean?.apply {
                // 标题信息
                tv_title.text = title
                tv_genres.text = "电影类型："
                tv_genres.appendTextList(genres)
                tv_aka.text = "别名："
                tv_aka.appendTextList(aka)
                tv_countries.text = "上映国家："
                tv_countries.appendTextList(countries)
                tv_year.text = "上映时间：${year}年"
                // 评分
                tv_score_number.text = rating.average.toString()
                tv_score_count.text = "${ratings_count}人评价"
                rb_score.rating = (rating.average / 2f).toFloat()
                // 电影海报
                iv_movie.load(images.large, 4f, listener = {
                    it?.apply {
                        // 设置状态栏颜色
                        window.statusBarColor = getSwatchColor()
                        ctl_movie.setContentScrimColor(getSwatchColor())
                        ctl_movie.setBackgroundColor(getSwatchColor())
                        tv_title.setTextColor(getSwatchColor())
                    }
                })
                // 简介
                tv_summary_title.setVisible(true)
                tv_summary.text = summary
                tv_summary_more.setVisible(true)
                tv_summary_more.setOnClickListener {
                    if (isSummaryExpend) {
                        tv_summary.setLines(5)
                        tv_summary.ellipsize = TextUtils.TruncateAt.END
                        tv_summary_more.text = "更多"
                        isSummaryExpend = false
                    } else {
                        tv_summary.setSingleLine(false)
                        tv_summary.ellipsize = null
                        tv_summary_more.text = "收起"
                        isSummaryExpend = true
                    }
                }
            }
        }

        mViewModel.bindActData(this) { actList ->
            // 影人
            tv_actor_title.setVisible(true)
            rv_actor.linear {
                orientation(androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL)
                renderItemsByDsl(actList) { person ->
                    xml(R.layout.item_act_card)
                    render {
                        it.iv_act.load(person.avatars.medium)
                        it.iv_act.tagEnable = person.isDirector
                        it.tv_name.text = person.name
                        it.setOnClickListener {
                            toastInfo(person.name)
                        }
                    }
                }
            }
        }

        mViewModel.bindFavoriteData(this) { isFavorite ->
            if (isFavorite == null) {
                return@bindFavoriteData
            }
            fab_favorite.setImageResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
        }
    }

}