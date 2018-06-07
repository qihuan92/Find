package com.qihuan.moviemodule.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvpActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.*
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieDetContract
import com.qihuan.moviemodule.model.bean.PersonBean
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.presenter.MovieDetPresenter
import com.qihuan.moviemodule.view.adapter.ActCardAdapter
import kotlinx.android.synthetic.main.activity_movie_det.*
import kotlinx.android.synthetic.main.include_movie_title.*

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_DET_ACTIVITY)
class MovieDetActivity : BaseMvpActivity<MovieDetContract.View, MovieDetContract.Presenter>(), MovieDetContract.View {

    @JvmField
    @Autowired
    var id: String = ""

    private var isSummaryExpend = false
    private lateinit var actCardAdapter: ActCardAdapter

    override fun initPresenter(): MovieDetContract.Presenter {
        return MovieDetPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_det)
        ARouter.getInstance().inject(this)
        initView()
        mPresenter.getSubject(id)
        mPresenter.getFavoriteMovie(id)
    }

    private fun initView() {
        //取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        setToolBar(toolbar)

        rv_actor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        actCardAdapter = ActCardAdapter()
        rv_actor.adapter = actCardAdapter

        fab_favorite.setOnClickListener { mPresenter.updateFavoriteMovie(id) }
    }

    @SuppressLint("SetTextI18n")
    override fun onSubject(subjectBean: SubjectBean) {

        subjectBean.apply {
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
                    //设置状态栏颜色
                    window.statusBarColor = getColor()
                    ctl_movie.setContentScrimColor(getColor())
                    ctl_movie.setBackgroundColor(getColor())
                    tv_title.setTextColor(getColor())
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

    override fun onAct(actList: List<PersonBean>) {
        // 影人
        tv_actor_title.setVisible(true)
        actCardAdapter.setNewData(actList)
    }

    override fun onFavoriteChange(isFavorite: Boolean) {
        fab_favorite.setImageResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
    }

    override fun showUpdateFavoriteInfo(isFavorite: Boolean) {
        toastSuccess(if (isFavorite) "收藏成功" else "取消收藏成功")
    }

}