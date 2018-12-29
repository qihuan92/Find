package com.qihuan.banner

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes

/**
 * IndicatorLayout
 * @author qi
 * @date 2018/10/23
 */
class IndicatorLayout : LinearLayout {

    @DrawableRes
    private var dotDrawableResId: Int = 0
    private var totalSize: Int = 0
    private var selectedPosition: Int = 0

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
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
    }

    fun refresh(@DrawableRes drawableResId: Int, totalSize: Int, dotMarginHorizontal: Int, dotMarginVertical: Int) {
        this.dotDrawableResId = drawableResId
        this.totalSize = totalSize

        removeAllViews()
        var dotView: ImageView
        val layoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(dotMarginHorizontal, dotMarginVertical, dotMarginHorizontal, dotMarginVertical)
        for (i in 0 until totalSize) {
            dotView = ImageView(context)
            dotView.layoutParams = layoutParams
            dotView.setImageResource(drawableResId)
            addView(dotView)
        }

        setSelectedPosition(0)
    }

    fun setSelectedPosition(position: Int) {
        this.selectedPosition = position
        for (i in 0 until totalSize) {
            getChildAt(i).isEnabled = i == position
            getChildAt(i).requestLayout()
        }
    }
}