package com.qihuan.find.view.adapter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.jetbrains.annotations.NotNull


/**
 * MainPageAdapter
 * @author qi
 * @date 2018/6/4
 */
class MainPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(@NotNull owner: LifecycleOwner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(@NotNull owner: LifecycleOwner) {
        FragmentFactory.clear()
    }

    override fun getItem(position: Int): Fragment {
        return FragmentFactory.createFragment(position)
    }

    override fun getCount(): Int {
        return 4
    }
}