package com.qihuan.moviemodule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseMvvmFragment
import com.qihuan.commonmodule.router.Routes
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.MovieListType
import com.qihuan.moviemodule.model.bean.SubjectBean
import com.qihuan.moviemodule.model.vo.SectionVO
import com.qihuan.moviemodule.view.cell.MovieBannerCell
import com.qihuan.moviemodule.view.cell.MovieCell
import com.qihuan.moviemodule.view.cell.SectionCell
import com.qihuan.moviemodule.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Routes.MOVIE_FRAGMENT)
class MovieFragment : BaseMvvmFragment<MovieViewModel>(MovieViewModel::class.java) {

    private var adapter: MultiTypeAdapter? = null
    private val itemList: Items = Items()

    fun <T> LiveData<T>.observeX(block: (T) -> Unit) {
        this.observe(this@MovieFragment, Observer {
            it?.let(block)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        mViewModel.getMovieData()
    }

    private fun initView() {
        // list
        rv_list.layoutManager = LinearLayoutManager(context)

        // adapter
        adapter = MultiTypeAdapter(itemList)
        adapter?.register(Array<SubjectBean>::class, MovieBannerCell())
        adapter?.register(SubjectBean::class, MovieCell())
        adapter?.register(SectionVO::class, SectionCell())
        rv_list.adapter = adapter

        mViewModel.movieData.observeX { data ->
            data.inTheaters.let { movie ->
                itemList.add(SectionVO(movie.title, true, MovieListType.IN_THEATERS))
                itemList.add(movie.subjects)
            }

            data.topMovie.let { movie ->
                itemList.add(SectionVO(movie.title, true, MovieListType.TOP_MOVIE))
                itemList.addAll(movie.subjects)
            }

            data.usBox.let { movie ->
                itemList.add(SectionVO(movie.title, false))
                movie.subjects.forEach { subjectsBean ->
                    itemList.add(subjectsBean.subject)
                }
            }

            adapter?.notifyDataSetChanged()
        }
    }
}
