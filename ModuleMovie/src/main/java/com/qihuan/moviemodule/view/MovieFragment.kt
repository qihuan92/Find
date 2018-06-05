package com.qihuan.moviemodule.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseMvpFragment
import com.qihuan.commonmodule.router.Router
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.USboxBean
import com.qihuan.moviemodule.presenter.MoviePresenter
import com.qihuan.moviemodule.view.adapter.MovieCardAdapter
import com.qihuan.moviemodule.view.adapter.MovieRankingAdapter
import com.qihuan.moviemodule.view.adapter.UsBoxMovieRankingAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Router.MOVIE_FRAGMENT)
class MovieFragment : BaseMvpFragment<MovieContract.View, MovieContract.Presenter>(), MovieContract.View {

    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var topMovieAdapter: MovieRankingAdapter
    private lateinit var usBoxAdapter: UsBoxMovieRankingAdapter

    override fun initPresenter(): MovieContract.Presenter {
        return MoviePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 热映
        movieCardAdapter = MovieCardAdapter()
        rv_in_theaters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_in_theaters.adapter = movieCardAdapter
        // top 250
        topMovieAdapter = MovieRankingAdapter()
        rv_top_movie.layoutManager = LinearLayoutManager(context)
        rv_top_movie.adapter = topMovieAdapter
        // us box
        usBoxAdapter = UsBoxMovieRankingAdapter()
        rv_us_box.layoutManager = LinearLayoutManager(context)
        rv_us_box.adapter = usBoxAdapter

        mPresenter.getInTheaters()
        mPresenter.getTopMovie()
        mPresenter.getUsBox()
    }

    override fun onInTheaters(moviesBean: MoviesBean) {
        tv_in_theaters.text = moviesBean.title
        movieCardAdapter.setNewData(moviesBean.subjects)
    }

    override fun onTopMovie(moviesBean: MoviesBean) {
        tv_top_movie.text = moviesBean.title
        topMovieAdapter.setNewData(moviesBean.subjects)
    }

    override fun onUsBox(usboxBean: USboxBean) {
        tv_us_box.text = usboxBean.title
        usBoxAdapter.setNewData(usboxBean.subjects)
    }
}
