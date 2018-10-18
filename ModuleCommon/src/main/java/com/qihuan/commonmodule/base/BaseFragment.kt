package com.qihuan.commonmodule.base

import androidx.fragment.app.Fragment

import com.qihuan.commonmodule.bus.BindEventBus

import org.greenrobot.eventbus.EventBus

/**
 * BaseFragment
 *
 * @author Qi
 * @date 2017/6/22
 */

abstract class BaseFragment : androidx.fragment.app.Fragment() {

    override fun onStart() {
        super.onStart()
        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
            EventBus.getDefault().unregister(this)
        }
    }

}
