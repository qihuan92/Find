package com.qihuan.banner

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.ViewPager
import java.lang.ref.WeakReference

/**
 * BannerLayout
 * @author qi
 * @date 2018/10/22
 */
class BannerLayout<T> : RelativeLayout, ViewPager.OnPageChangeListener {

    private var viewPager: BannerViewPager? = null
    private var adapter: BannerAdapter<T>? = null
    private var dataList: List<T> = emptyList()
    private var isInfinite: Boolean = true
    private var indicatorLayout: IndicatorLayout? = null
    @DrawableRes
    private var dotDrawableResId: Int = 0
    private var dotMarginHorizontal: Int = context.dp2px(3f)
    private var dotMarginVertical: Int = context.dp2px(3f)
    private var autoPlayTask: AutoPlayTask<T>? = null
    var intervalMillis: Long = 3000
    var isAutoPlay: Boolean = true

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
        autoPlayTask = AutoPlayTask(this)

        viewPager = BannerViewPager(context)
        viewPager?.addOnPageChangeListener(this)
        adapter = BannerAdapter()
        viewPager?.adapter = adapter
        viewPager?.offscreenPageLimit = 1
        val lpVp = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(viewPager, 0, lpVp)

        indicatorLayout = IndicatorLayout(context)
        val lpIndicator = RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lpIndicator.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        lpIndicator.addRule(RelativeLayout.CENTER_HORIZONTAL)
        addView(indicatorLayout, lpIndicator)
    }

    private fun initData() {
        adapter?.dataList = dataList
        if (isInfinite) {
            viewPager?.currentItem = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % dataList.size
        }
        adapter?.notifyDataSetChanged()
        indicatorLayout?.refresh(dotDrawableResId, dataList.size, dotMarginHorizontal, dotMarginVertical)
    }

    fun setItemView(@LayoutRes layoutResId: Int) {
        adapter?.itemResId = layoutResId
    }

    fun setData(dataList: List<T>) {
        this.dataList = dataList
        initData()
    }

    fun loadItem(listener: ((view: View, data: T, position: Int) -> Unit)) {
        adapter?.loadListener = listener
    }

    fun itemClick(listener: ((view: View, data: T, position: Int) -> Unit)) {
        adapter?.clickListener = listener
    }

    fun infinite(isInfinite: Boolean) {
        this.isInfinite = isInfinite
        adapter?.isInfinite = isInfinite
    }

    fun setDot(@DrawableRes drawableResId: Int = 0, marginHorizontal: Int = context.dp2px(3f), marginVertical: Int = context.dp2px(3f)) {
        this.dotDrawableResId = drawableResId
        dotMarginHorizontal = marginHorizontal
        dotMarginVertical = marginVertical
    }

    fun nextPage() {
        viewPager?.currentItem = viewPager?.currentItem?.plus(1) ?: 0
    }

    fun startAutoPlay() {
        stopAutoPlay()
        if (isAutoPlay) {
            postDelayed(autoPlayTask, intervalMillis)
        }
    }

    fun stopAutoPlay() {
        if (autoPlayTask != null) {
            removeCallbacks(autoPlayTask)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            startAutoPlay()
        } else {
            stopAutoPlay()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAutoPlay()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAutoPlay()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (isInfinite) {
            when (ev?.action) {
                MotionEvent.ACTION_DOWN -> {
                    stopAutoPlay()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    startAutoPlay()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        val realPosition = position % dataList.size
        indicatorLayout?.setSelectedPosition(realPosition)
    }

    class AutoPlayTask<T>(bannerLayout: BannerLayout<T>) : Runnable {

        private var wrBannerLayout: WeakReference<BannerLayout<T>>? = null

        init {
            wrBannerLayout = WeakReference(bannerLayout)
        }

        override fun run() {
            val bannerLayout = wrBannerLayout?.get()
            bannerLayout?.run {
                nextPage()
                startAutoPlay()
            }
        }
    }
}