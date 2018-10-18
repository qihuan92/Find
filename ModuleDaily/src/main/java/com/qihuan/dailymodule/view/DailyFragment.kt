package com.qihuan.dailymodule.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.qihuan.commonmodule.base.BaseMvpFragment
import com.qihuan.commonmodule.bus.BindEventBus
import com.qihuan.commonmodule.bus.event.RefreshEvent
import com.qihuan.commonmodule.router.Routes
import com.qihuan.dailymodule.R
import com.qihuan.dailymodule.contract.DailyContract
import com.qihuan.dailymodule.model.bean.StoryBean
import com.qihuan.dailymodule.model.bean.TopStoryBean
import com.qihuan.dailymodule.presenter.DailyPresenter
import com.qihuan.dailymodule.view.cell.BannerCell
import com.qihuan.dailymodule.view.cell.DailyCell
import com.qihuan.dailymodule.view.cell.DailySectionCell
import kotlinx.android.synthetic.main.fragment_news.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register
import org.greenrobot.eventbus.Subscribe

/**
 * DailyFragment
 *
 * @author Qi
 */
@BindEventBus
@Route(path = Routes.DAILY_FRAGMENT)
class DailyFragment : BaseMvpFragment<DailyContract.View, DailyContract.Presenter>(), DailyContract.View {

    private var dailyAdapter: MultiTypeAdapter? = null
    private var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null
    private val itemList: Items = Items()

    override fun initPresenter(): DailyContract.Presenter {
        return DailyPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // list
        linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        rv_list.layoutManager = linearLayoutManager

        // adapter
        dailyAdapter = MultiTypeAdapter(itemList)
        dailyAdapter?.register(Array<TopStoryBean>::class, BannerCell())
        dailyAdapter?.register(StoryBean::class, DailyCell())
        dailyAdapter?.register(String::class, DailySectionCell())
        rv_list.adapter = dailyAdapter

        // refresh layout
        refresh_layout.apply {
            setOnRefreshListener { mPresenter.getLatestDaily() }
            setOnLoadMoreListener { mPresenter.getBeforeDaily() }
            autoRefresh()
        }
    }

    override fun onBannerData(itemList: Array<TopStoryBean>) {
        this.itemList.add(itemList)
        dailyAdapter?.notifyItemInserted(0)
    }

    override fun onDailyData(itemList: Array<StoryBean>) {
        val start = this.itemList.size
        this.itemList.addAll(itemList)
        dailyAdapter?.notifyItemRangeInserted(start, itemList.size)
    }

    override fun onDailySectionData(item: String) {
        val start = this.itemList.size
        this.itemList.add(item)
        dailyAdapter?.notifyItemInserted(start)
    }

    override fun onRefreshStart() {
        val count = this.itemList.size
        this.itemList.clear()
        dailyAdapter?.notifyItemRangeRemoved(0, count)
    }

    override fun onRefreshEnd(success: Boolean) {
        refresh_layout.finishRefresh(success)
    }

    override fun onLoadMoreEnd(success: Boolean) {
        refresh_layout.finishLoadMore(success)
    }

    @Subscribe
    fun onRefreshEvent(@Suppress("UNUSED_PARAMETER") refreshEvent: RefreshEvent) {
        linearLayoutManager?.run {
            val visibleItemPosition = findFirstCompletelyVisibleItemPosition()
            if (visibleItemPosition == 0) {
                // 刷新
                refresh_layout.autoRefresh()
                return
            }
            // 超过 20 条, 先滚动到20条, 再平滑滚动
            if (visibleItemPosition > 20) {
                rv_list.scrollToPosition(20)
            }
            rv_list.smoothScrollToPosition(0)
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMsg: String) {

    }

}
