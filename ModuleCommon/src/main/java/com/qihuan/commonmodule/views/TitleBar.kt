package com.qihuan.commonmodule.views

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.ImageButton
import com.qihuan.commonmodule.R

/**
 * TitleBar
 * @author qi
 * @date 2018/6/7
 */
class TitleBar : Toolbar {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }

    private fun init() {
        val ibBack = ImageButton(context)
        ibBack.setImageResource(R.drawable.ic_back_white)
//        ibBack.setBackgroundResource(android.R.attr.selectableItemBackgroundBorderless)
    }
}