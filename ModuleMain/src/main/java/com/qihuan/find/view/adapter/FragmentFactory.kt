package com.qihuan.find.view.adapter

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Router

/**
 * FragmentFactory
 * @author qi
 * @date 2018/6/4
 */
class FragmentFactory {
    companion object {
        var fragmentMap: HashMap<Int, Fragment> = HashMap()

        fun createFragment(position: Int): Fragment {
            var fragment = fragmentMap[position]
            if (fragment == null) {
                fragment = when (position) {
                    0 -> ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation() as Fragment
                    1 -> ARouter.getInstance().build(Router.MOVIE_FRAGMENT).navigation() as Fragment
                    2 -> ARouter.getInstance().build(Router.DISCOVER_FRAGMENT).navigation() as Fragment
                    3 -> ARouter.getInstance().build(Router.USER_FRAGMENT).navigation() as Fragment
                    else -> ARouter.getInstance().build(Router.DAILY_FRAGMENT).navigation() as Fragment
                }
                fragmentMap[position] = fragment
                return fragment
            }
            return fragment
        }
    }
}