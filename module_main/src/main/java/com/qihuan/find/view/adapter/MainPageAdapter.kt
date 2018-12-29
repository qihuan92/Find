package com.qihuan.find.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.annotations.NotNull


/**
 * MainPageAdapter
 * @author qi
 * @date 2018/6/4
 */
class MainPageAdapter(fragmentManager: androidx.fragment.app.FragmentManager) : FragmentPagerAdapter(fragmentManager), LifecycleObserver {

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