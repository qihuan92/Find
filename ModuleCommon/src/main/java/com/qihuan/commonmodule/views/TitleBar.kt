package com.qihuan.commonmodule.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.qihuan.commonmodule.R
import com.qihuan.commonmodule.utils.dp2px


/**
 * TitleBar
 * @author qi
 * @date 2018/6/7
 */
class TitleBar : Toolbar {

    companion object {
        const val DARK = 0
        const val LIGHT = 1
    }

    private val titleText = ""
    private var ibBack: ImageButton? = null
    private var tvTitle: TextView? = null

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
        // container
        setContentInsetsAbsolute(0, 0)

        // back button
        ibBack = ImageButton(context)
        ibBack?.apply {
            setImageResource(R.drawable.ic_back_white)
            val attrs = intArrayOf(android.R.attr.selectableItemBackgroundBorderless)
            val ta = context.obtainStyledAttributes(attrs)
            val drawableFromTheme = ta.getDrawable(0)
            ta.recycle()
            background = drawableFromTheme
            val ibBackPadding = context.dp2px(10f)
            setPadding(ibBackPadding, ibBackPadding, ibBackPadding, ibBackPadding)
            addView(this, 0)
        }

        // title
        tvTitle = TextView(context)
        tvTitle?.apply {
            text = titleText
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            textSize = 18f
            gravity = Gravity.CENTER_VERTICAL
            setSingleLine(true)
            paint.isFakeBoldText = true
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            addView(this)
        }
    }

    fun setOnBackClickListener(listener: (view: View) -> Unit) {
        ibBack?.setOnClickListener {
            listener.invoke(it)
        }
    }

    fun setTitleText(title: String) {
        tvTitle?.text = title
    }

    fun setTheme(mode: Int) {
        when(mode) {
            DARK -> {
                ibBack?.setImageResource(R.drawable.ic_back_white)
                tvTitle?.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            }
            LIGHT -> {
                ibBack?.setImageResource(R.drawable.ic_back_black)
                tvTitle?.setTextColor(ContextCompat.getColor(context, android.R.color.black))
            }
        }
    }
}