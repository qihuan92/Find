package com.qihuan.find.view.adapter

import com.alibaba.android.arouter.launcher.ARouter
import com.qihuan.commonmodule.router.Routes

/**
 * FragmentFactory
 * @author qi
 * @date 2018/6/4
 */
class FragmentFactory {
    companion object {
        var fragmentMap: HashMap<Int, androidx.fragment.app.Fragment> = HashMap()

        fun createFragment(position: Int): androidx.fragment.app.Fragment {
            var fragment = fragmentMap[position]
            if (fragment == null) {
                fragment = when (position) {
                    0 -> ARouter.getInstance().build(Routes.DAILY_FRAGMENT).navigation()
                    1 -> ARouter.getInstance().build(Routes.MOVIE_FRAGMENT).navigation()
                    2 -> ARouter.getInstance().build(Routes.DISCOVER_FRAGMENT).navigation()
                    3 -> ARouter.getInstance().build(Routes.USER_FRAGMENT).navigation()
                    else -> ARouter.getInstance().build(Routes.DAILY_FRAGMENT).navigation()
                } as androidx.fragment.app.Fragment
                fragmentMap[position] = fragment
                return fragment
            }
            return fragment
        }

        fun clear() {
            fragmentMap.clear()
        }
    }
}