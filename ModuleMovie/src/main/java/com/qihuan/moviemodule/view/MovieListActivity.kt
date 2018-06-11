package com.qihuan.moviemodule.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.load
import com.qihuan.commonmodule.utils.toastError
import com.qihuan.commonmodule.views.TitleBar
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.MovieListType
import com.qihuan.moviemodule.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.item_movie.view.*
import zlc.season.yaksa.linear

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_LIST_ACTIVITY)
class MovieListActivity : BaseActivity() {

    private val mViewModel by lazy { ViewModelProviders.of(this).get(MovieListViewModel::class.java) }

    @JvmField
    @Autowired(name = Routes.MOVIE_LIST_ACTIVITY_EXTRA_TYPE)
    var type: Int = MovieListType.IN_THEATERS

    @JvmField
    @Autowired(name = Routes.MOVIE_LIST_ACTIVITY_EXTRA_TITLE)
    var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {
        mStatusBar.barColor(android.R.color.white).statusBarDarkFont(true, 0.2f).init()
        setToolBar(toolbar, title, theme = TitleBar.LIGHT, color = android.R.color.white)

        refresh_layout.setOnRefreshListener {
            mViewModel.clearData()
            getDataList()
        }
        refresh_layout.setOnLoadMoreListener {
            getDataList()
        }
        refresh_layout.autoRefresh()
        mViewModel.bindUIState(this) {
            when (it) {
                MovieListViewModel.UIState.ERROR -> {
                    toastError()
                    refresh_layout.finishRefresh(false)
                    refresh_layout.finishLoadMore(false)
                }
                MovieListViewModel.UIState.REFRESH_FINISH -> refresh_layout.finishRefresh(true)
                MovieListViewModel.UIState.LOAD_FINISH -> refresh_layout.finishLoadMore(true)
            }
        }
        mViewModel.bindMovieData(this) { subjectList->
            rv_list.linear {
                subjectList?.forEach { subject ->
                    itemDsl {
                        xml(R.layout.item_movie)
                        render {
                            subject.apply {
                                it.iv_item_movie.load(images.small, 4f)
                                it.tv_item_movie.text = title
                                it.tv_item_year.text = "[$year]"
                                it.tv_item_score.text = rating.average.toString()
                                it.rb_item_score.rating = (rating.average / 2f).toFloat()
                                it.tag_item_genres.tags = genres
                                it.tv_item_original_title.text = original_title
                                it.setOnClickListener {
                                    start(id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getDataList() {
        when (type) {
            MovieListType.IN_THEATERS -> mViewModel.getInTheaters()
            MovieListType.TOP_MOVIE -> mViewModel.getTopMovie()
        }
    }

    private fun start(id: String) {
        ARouter.getInstance()
                .build(Routes.MOVIE_DET_ACTIVITY)
                .withString(Routes.MOVIE_DET_ACTIVITY_EXTRA_ID, id)
                .navigation()
    }
}