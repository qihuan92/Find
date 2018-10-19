package com.qihuan.commonmodule.views

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * NoScrollViewPager
 * @author qi
 * @date 2018/6/5
 */
class NoScrollViewPager : androidx.viewpager.widget.ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}