package com.qihuan.discovermodule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseFragment
import com.qihuan.commonmodule.router.Router
import com.qihuan.discovermodule.R

/**
 * DiscoverFragment
 *
 * @author Qi
 */
@Route(path = Router.DISCOVER_FRAGMENT)
class DiscoverFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    companion object {

        fun newInstance(): DiscoverFragment {
            return DiscoverFragment()
        }
    }

}
