package com.qihuan.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter

/**
 * BannerAdapter
 * @author qi
 * @date 2018/10/22
 */
class BannerAdapter<T> : PagerAdapter() {

    @LayoutRes
    var itemResId: Int = 0
    var dataList: List<T> = emptyList()
    var isInfinite = true
    var loadListener: ((view: View, data: T, position: Int) -> Unit)? = null
    var clickListener: ((view: View, data: T, position: Int) -> Unit)? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return if (isInfinite) Int.MAX_VALUE else dataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (itemResId == 0) {
            throw RuntimeException("item resId is null!")
        }
        val view = LayoutInflater.from(container.context).inflate(itemResId, container, false)
        loadListener?.invoke(view, dataList[getPosition(position)], getPosition(position))
        view.setOnClickListener { v ->
            clickListener?.invoke(v, dataList[getPosition(position)], getPosition(position))
        }
        container.addView(view)
        return view
    }

    private fun getPosition(position: Int): Int {
        return position % dataList.size
    }
}