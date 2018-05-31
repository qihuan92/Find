package com.qihuan.usermodule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseFragment
import com.qihuan.commonmodule.router.Router
import com.qihuan.usermodule.R

/**
 * MeFragment
 *
 * @author Qi
 */
@Route(path = Router.USER_FRAGMENT)
class MeFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    companion object {

        fun newInstance(): MeFragment {
            return MeFragment()
        }
    }

}
