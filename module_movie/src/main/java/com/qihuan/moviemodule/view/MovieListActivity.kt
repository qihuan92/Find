package com.qihuan.moviemodule.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.base.BaseMvvmActivity
import com.qihuan.commonmodule.router.Routes
import com.qihuan.commonmodule.utils.toastError
import com.qihuan.commonmodule.views.TitleBar
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.MovieListType
import com.qihuan.moviemodule.view.adapter.MovieListAdapter
import com.qihuan.moviemodule.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*

/**
 * MovieDetActivity
 * @author qi
 * @date 2018/6/6
 */
@Route(path = Routes.MOVIE_LIST_ACTIVITY)
class MovieListActivity : BaseMvvmActivity<MovieListViewModel>(MovieListViewModel::class.java) {

    private var adapter: MovieListAdapter? = null

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

        rv_list.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter()
        rv_list.adapter = adapter

        mViewModel.bindMovieData(this) { subjectList ->
            adapter?.setMovieList(subjectList)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun getDataList() {
        when (type) {
            MovieListType.IN_THEATERS -> mViewModel.getInTheaters()
            MovieListType.TOP_MOVIE -> mViewModel.getTopMovie()
        }
    }
}