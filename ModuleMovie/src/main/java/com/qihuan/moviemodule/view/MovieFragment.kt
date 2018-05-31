package com.qihuan.moviemodule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseFragment
import com.qihuan.commonmodule.router.Router
import com.qihuan.moviemodule.R

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Router.MOVIE_FRAGMENT)
class MovieFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }
}
