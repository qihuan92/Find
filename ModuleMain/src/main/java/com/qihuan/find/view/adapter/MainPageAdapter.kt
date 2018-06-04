package com.qihuan.find.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * MainPageAdapter
 * @author qi
 * @date 2018/6/4
 */
class MainPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return FragmentFactory.createFragment(position)
    }

    override fun getCount(): Int {
        return 4
    }
}