package com.qihuan.moviemodule.presenter

import com.qihuan.commonmodule.base.AbsRxPresenter
import com.qihuan.moviemodule.contract.MovieContract
import com.qihuan.moviemodule.model.DoubanApi
import com.qihuan.moviemodule.model.bean.MovieHomeBean
import com.qihuan.moviemodule.model.bean.MovieSectionItemBean
import com.qihuan.moviemodule.model.bean.MoviesBean
import com.qihuan.moviemodule.model.bean.USboxBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * MoviePresenter
 * @author qi
 * @date 2018/6/5
 */
class MoviePresenter : AbsRxPresenter<MovieContract.View>(), MovieContract.Presenter {
    override fun getMovieData() {
        Observable.zip(
                DoubanApi.get().getInTheaters(),
                DoubanApi.get().getTopMovie(count = 5),
                DoubanApi.get().getUsBox(),
                Function3<MoviesBean, MoviesBean, USboxBean, MovieHomeBean> { inTheaters, topMovie, usBox ->
                    MovieHomeBean(inTheaters, topMovie, usBox)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            val list = ArrayList<MovieSectionItemBean>()
                            // top
                            list.add(MovieSectionItemBean(true, it.topMovie.title))
                            it.topMovie.subjects?.map { MovieSectionItemBean(it) }.orEmpty().toList().forEachIndexed { index, item ->
                                item.t.rank = index + 1
                                list.add(item)
                            }
                            // us box
                            list.add(MovieSectionItemBean(true, it.usBox.title))
                            it.usBox.subjects?.map { MovieSectionItemBean(it.subject) }.orEmpty().toList().forEachIndexed { index, item ->
                                item.t.rank = index + 1
                                list.add(item)
                            }
                            view?.onData(it.inTheaters, list)
                        },
                        onError = {
                            view?.showError(it.message ?: "")
                        }
                )
    }

}